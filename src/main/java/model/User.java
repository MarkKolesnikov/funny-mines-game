package model;

import java.util.UUID;

public class User {

    private final UUID id;
    private String login;

    public User(String login) {
        this.id = UUID.randomUUID();
        this.login = login;
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
