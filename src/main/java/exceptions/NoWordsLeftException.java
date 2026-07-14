package exceptions;

public class NoWordsLeftException extends RuntimeException {
    public NoWordsLeftException() {
        super("Слова закончились: сыграно все доступные слова из колоды");
    }
}
