package service;

import exceptions.GameNotFoundException;
import exceptions.GameStatusException;
import exceptions.NotEnoughPlayersException;
import model.Game;
import model.GameStatus;
import model.User;
import org.springframework.stereotype.Service;
import store.GameRepository;

import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createGame(String login) {
        User user = createUserForGame(login); // Вынести лучше в отдельный UserService

        Game game = new Game();
        game.addUser(user);

        gameRepository.save(game);
        return game;
    }

    public Game joinGame(UUID gameId, String login) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));

        validateGameIsWaiting(game);

        User user = createUserForGame(login);
        validateUserNotExist(game, user);
        game.addUser(user);

        gameRepository.save(game);

        return game;
    }

    public Game startGame(UUID gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));

        validateGameIsWaiting(game);
        validateUserSize(game);

        game.setStatus(GameStatus.IN_PROCESSING);
        gameRepository.save(game);

        return game;
    }

    private User createUserForGame(String login) {
        return new User(login);
    }

    private void validateGameIsWaiting(Game game) {
        if (game.getStatus() != GameStatus.WAITING) {
            throw new GameStatusException(game.getStatus());
        }
    }

    private void validateUserNotExist(Game game, User user) {
        if (game.getUsers()
                .stream()
                .anyMatch(u -> u.getLogin().equals(user.getLogin()))) {
            throw new IllegalArgumentException("Пользователь уже в игре.");
        }
    }

    private void validateUserSize(Game game) {
        if (game.getUsers().size() < 2) {
            throw new NotEnoughPlayersException();
        }
    }
}
