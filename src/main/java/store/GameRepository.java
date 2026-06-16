package store;

import exceptions.GameNotFoundException;
import model.Game;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GameRepository {

    private final Map<UUID, Game> games = new ConcurrentHashMap<>();

    public void save(Game game) {
        games.put(game.getId(), game);
    }

    public Game findByIdOrThrow(UUID id) {
        Game game = games.get(id);
        if (game == null) {
            throw new GameNotFoundException(id);
        }
        return game;
    }
}