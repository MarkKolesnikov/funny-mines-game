package exceptions;

import java.util.UUID;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(UUID gameId) {
        super("Игра не найдена: " + gameId);
    }
}
