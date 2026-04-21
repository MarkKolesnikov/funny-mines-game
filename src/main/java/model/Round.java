package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static model.RoundStatus.PLACING_MINES;

public class Round {

    private final UUID id = UUID.randomUUID();
    private int roundNumber;
    private RoundStatus status = PLACING_MINES;
    private RoundResult result;
    private String secretWord;
    private Map<UUID, Role> roles;
    private List<Mine> mines = new ArrayList<>();


    public Round(int roundNumber,String secretWord, Map<UUID, Role> roles) {
        this.roundNumber = roundNumber;
        this.roles = roles;
        this.secretWord = secretWord;
    }

    public UUID getId() {
        return id;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public RoundStatus getStatus() {
        return status;
    }

    public RoundResult getResult() {
        return result;
    }

    public Map<UUID, Role> getRoles() {
        return roles;
    }

    public List<Mine> getMines() {
        return mines;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setStatus(RoundStatus status) {
        this.status = status;
    }

    public void setResult(RoundResult result) {
        this.result = result;
    }

    public void addMine (Mine mine) {
        mines.add(mine);
    }
}
