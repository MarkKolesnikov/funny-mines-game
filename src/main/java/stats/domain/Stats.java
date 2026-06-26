package stats.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Stats {

    private final UUID userId;

    @Setter
    private int score = 0;
    @Setter
    private int minesPlaced = 0;
    @Setter
    private int minesTriggered = 0;
    @Setter
    private int roundsWon = 0;

    public Stats(UUID userId) {
        this.userId = userId;
    }

}
