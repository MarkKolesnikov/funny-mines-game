package round.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mine.domain.Mine;

import java.util.*;

@Getter
@RequiredArgsConstructor
public class Round {

    private final int roundNumber;
    private final String secretWord;
    private final Map<UUID, Role> roles;

    @Setter
    private UUID guesserId;
    @Setter
    private UUID hintGiverId;
    @Setter
    private RoundStatus status = RoundStatus.PLACING_MINES;
    @Setter
    private RoundResult result;

    private final List<Mine> mines = new ArrayList<>();

    public void addMine(Mine mine) {

        if (status == RoundStatus.ROUND_FINISHED) {
            throw new IllegalStateException("Нельзя добавлять мины после завершения раунда");
        }
        mines.add(mine);
    }

}