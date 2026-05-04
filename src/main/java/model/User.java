package model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class User {

    private final UUID id;
    @Setter
    private String login;

    public User(String login) {
        this.id = UUID.randomUUID();
        this.login = login;
    }

}
