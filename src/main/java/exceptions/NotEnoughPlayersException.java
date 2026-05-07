package exceptions;

public class NotEnoughPlayersException extends RuntimeException {

    public NotEnoughPlayersException() {
        super("Не хватает игроков, чтобы начать игру. Требуется минимум 2");
    }
}