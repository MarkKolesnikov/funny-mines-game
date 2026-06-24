package store;

import exceptions.GameNotFoundException;
import model.Game;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GameRepository {

    private final Map<UUID, Game> games = new ConcurrentHashMap<>();

    public void save(Game game) {
        games.put(game.getId(), game);
    }

    public Optional<Game> findById(UUID id) {
        return Optional.ofNullable(games.get(id));
    }
}