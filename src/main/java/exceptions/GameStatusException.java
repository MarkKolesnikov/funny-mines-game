package exceptions;

import game.domain.GameStatus;

public class GameStatusException extends RuntimeException {

    public GameStatusException(GameStatus status) {
        super("Игра имеет неправильный статус: " + status);
    }
}