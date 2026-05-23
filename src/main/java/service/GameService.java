package service;

import exceptions.GameNotFoundException;
import exceptions.GameStatusException;
import exceptions.NotEnoughPlayersException;
import model.Game;
import model.GameStatus;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.GameStore;

import java.util.UUID;

@Service
public class GameService {

    private final GameStore gameStore;

    public GameService(GameStore store) {
        this.gameStore = store;
    }

    public Game createGame(String login) {
        User user = new User(login);

        Game game = new Game();
        game.addUser(user);

        gameStore.save(game);

        return game;
    }

    public Game joinGame(UUID gameId, String login) {
        Game game = gameStore.findById(gameId); //дублирование

        if (game == null) {
            throw new GameNotFoundException(gameId);
        }

        if (game.getStatus() != GameStatus.WAITING) {
            throw new GameStatusException(game.getStatus());
        }

        User user = new User(login);
        game.addUser(user);

        return game;
    }

    public Game startGame(UUID gameId) {
        Game game = gameStore.findById(gameId); //дублирование

        if (game == null) {
            throw new GameNotFoundException(gameId);
        }

        if (game.getStatus() != GameStatus.WAITING) {
            throw new GameStatusException(game.getStatus());
        }

        if (game.getUsers().size() < 2) {
            throw new NotEnoughPlayersException();
        }

        game.setStatus(GameStatus.IN_PROCESSING);

        return game;
    }
}
