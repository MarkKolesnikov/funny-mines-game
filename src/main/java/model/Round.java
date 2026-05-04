package model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@RequiredArgsConstructor
public class Round {

    private final UUID id = UUID.randomUUID();
    private final int roundNumber;
    private final String secretWord;

    private final Map<UUID, Role> roles;
    @Setter private UUID guesserId;
    @Setter private UUID hintGiverId;

    @Setter private RoundStatus status = RoundStatus.PLACING_MINES;
    @Setter private RoundResult result;

    private final List<Mine> mines = new ArrayList<>();

    public void addMine(Mine mine) {
        mines.add(mine);
    }
}