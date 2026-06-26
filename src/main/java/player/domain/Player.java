package player.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Player {

    private final UUID id;

    @Setter
    private String login;

    public Player(final String login) {
        this.id = UUID.randomUUID();
        this.login = login;
    }
}
