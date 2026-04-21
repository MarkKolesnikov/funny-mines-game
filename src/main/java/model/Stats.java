package model;

import java.util.UUID;

public class Stats {

    private final UUID userId;
    private int score = 0;
    private int minesPlaced = 0;
    private int minesTriggered = 0;
    private int roundsWon = 0;

    public Stats(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    public int getMinesPlaced() {
        return minesPlaced;
    }

    public int getMinesTriggered() {
        return minesTriggered;
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMinesPlaced(int minesPlaced) {
        this.minesPlaced = minesPlaced;
    }

    public void setMinesTriggered(int minesTriggered) {
        this.minesTriggered = minesTriggered;
    }

    public void setRoundsWon(int roundsWon) {
        this.roundsWon = roundsWon;
    }
}
