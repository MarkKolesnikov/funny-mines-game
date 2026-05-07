package store;

import model.Game;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class GameStore {

    private final Map<UUID, Game> games = new HashMap<>();

    public void save(Game game) {
        games.put(game.getId(), game);
    }

    public Game findById(UUID id) {
        return games.get(id); // вернёт null если не найдена
    }
}