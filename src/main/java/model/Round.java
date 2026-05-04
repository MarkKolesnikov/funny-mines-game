package model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static model.RoundStatus.PLACING_MINES;

@Getter
public class Round {
    private final UUID id = UUID.randomUUID();
    private final int roundNumber;
    @Setter
    private RoundStatus status = PLACING_MINES;
    @Setter
    private RoundResult result;
    private final String secretWord;
    @Setter
    private UUID guesserId;
    @Setter
    private UUID hintGiverId;
    private final Map<UUID, Role> roles;
    private final List<Mine> mines = new ArrayList<>();

    public Round(int roundNumber, String secretWord, Map<UUID, Role> roles) {
        this.roundNumber = roundNumber;
        this.roles = roles;
        this.secretWord = secretWord;
    }

    public void addMine(Mine mine) {
        mines.add(mine);
    }
}
