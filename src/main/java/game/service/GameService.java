package game.service;

import exceptions.GameNotFoundException;
import exceptions.GameStatusException;
import exceptions.NotEnoughPlayersException;
import game.domain.Game;
import game.domain.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import player.domain.Player;
import org.springframework.stereotype.Service;
import player.service.PlayerService;
import game.repository.GameRepository;

import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private static final int MIN_PLAYERS_TO_START = 2;

    @Autowired
    public GameService(GameRepository gameRepository, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
    }


    public Game createGame(String login) { // ВЫНЕСТИ В ОТДЕЛЬНУЮ ФАБРИКУ
        Player player = playerService.createPlayer(login);

        Game game = new Game();
        game.addPlayer(player);

        return save(game);
    }

    public Game joinGame(UUID gameId, String login) {
        Game game = findAndValidateWaitingGame(gameId);

        Player player = playerService.createPlayer(login);
        checkNoDuplicatePlayer(game, player);
        game.addPlayer(player);

        return save(game);
    }

    public Game startGame(UUID gameId) {
        Game game = findAndValidateWaitingGame(gameId);
        validatePlayerSize(game);
        game.setStatus(GameStatus.IN_PROCESSING);

        return save(game);
    }

    private Game save(Game game) {
        gameRepository.save(game);
        return game;
    }

    private Game findAndValidateWaitingGame(UUID gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));

        if (game.getStatus() != GameStatus.WAITING) {
            throw new GameStatusException(game.getStatus());
        }

        return game;
    }

    private void checkNoDuplicatePlayer(Game game, Player player) {
        if (game.getPlayers()
                .stream()
                .anyMatch(p -> p.getLogin().equals(player.getLogin()))) {
            throw new IllegalArgumentException("Игрок уже в игре.");
        }
    }

    private void validatePlayerSize(Game game) {
        if (game.getPlayers().size() < MIN_PLAYERS_TO_START) {
            throw new NotEnoughPlayersException();
        }
    }
}
