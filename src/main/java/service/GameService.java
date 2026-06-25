package service;

import exceptions.GameNotFoundException;
import exceptions.GameStatusException;
import exceptions.NotEnoughPlayersException;
import model.Game;
import model.GameStatus;
import model.Player;
import org.springframework.stereotype.Service;
import store.GameRepository;

import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;

    public GameService(GameRepository gameRepository, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
    }


    public Game createGame(String login) {
        Player player = playerService.createPlayer(login);

        Game game = new Game();
        game.addPlayer(player);

        gameRepository.save(game);
        return game;
    }

    public Game joinGame(UUID gameId, String login) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));

        validateGameIsWaiting(game);

        Player player = playerService.createPlayer(login);
        validatePlayerNotExist(game, player);
        game.addPlayer(player);

        gameRepository.save(game);

        return game;
    }

    public Game startGame(UUID gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));

        validateGameIsWaiting(game);
        validatePlayerSize(game);

        game.setStatus(GameStatus.IN_PROCESSING);
        gameRepository.save(game);

        return game;
    }

    private void validateGameIsWaiting(Game game) {
        if (game.getStatus() != GameStatus.WAITING) {
            throw new GameStatusException(game.getStatus());
        }
    }

    private void validatePlayerNotExist(Game game, Player player) {
        if (game.getPlayers()
                .stream()
                .anyMatch(u -> u.getLogin().equals(player.getLogin()))) {
            throw new IllegalArgumentException("Пользователь уже в игре.");
        }
    }

    private void validatePlayerSize(Game game) {
        if (game.getPlayers().size() < 2) {
            throw new NotEnoughPlayersException();
        }
    }
}
