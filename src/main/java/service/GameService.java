package service;

import exceptions.GameStatusException;
import exceptions.NotEnoughPlayersException;
import model.Game;
import model.GameStatus;
import model.User;
import org.springframework.stereotype.Service;
import store.GameRepository;

import java.util.Objects;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository store) {
        this.gameRepository = store;
    }

    public Game createGame(String login) {
        User user = createUser(login);// Вынести в отдельно

        Game game = new Game();
        game.addUser(user);

        gameRepository.save(game);

        return game;
    }

    public Game joinGame(UUID gameId, String login) {
        Game game = gameRepository.findById(gameId);

        validateGameIsWaiting(game);

        User user = createUser(login);
        game.addUser(user);

        return game;
    }

    public Game startGame(UUID gameId) {
        Game game = gameRepository.findById(gameId);

        validateGameIsWaiting(game);

        validateUserSize(game);

        game.setStatus(GameStatus.IN_PROCESSING);

        return game;
    }

    private User createUser(String login) {
        return new User(login);
    }

    private void validateGameIsWaiting(Game game) {
        if (game.getStatus() != GameStatus.WAITING) {
            throw new GameStatusException(game.getStatus());
        }
    }

    private void validateUserSize(Game game) {
        if (game.getUsers().size() < 2) {
            throw new NotEnoughPlayersException();
        }
    }
}
