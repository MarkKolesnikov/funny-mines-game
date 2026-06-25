package service;

import model.Player;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    public Player createPlayer(String login) {
        validateLoginBlankOrNot(login);
        return new Player(login);
    }

    private void validateLoginBlankOrNot(String login) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Логин не может быть пустой");
        }
    }
}
