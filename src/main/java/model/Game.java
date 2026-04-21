package model;

import java.util.*;

import static model.GameStatus.WAITING;

public class Game {

    private final UUID id = UUID.randomUUID();
    private GameStatus status = WAITING;
    private List<User> users = new ArrayList<>();
    private Round currentRound;
    private Map<UUID, Stats> leaderBoard = new HashMap<>();


    public UUID getId() {
        return id;
    }

    public GameStatus getStatus() {
        return status;
    }

    public List<User> getUsers() {
        return users;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public Map<UUID, Stats> getLeaderBoard() {
        return leaderBoard;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
