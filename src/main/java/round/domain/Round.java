package round.domain;

import lombok.Getter;
import lombok.Setter;
import mine.domain.Mine;

import java.util.*;

@Getter
public class Round {

    private final UUID gameId;
    private final String secretWord;
    // Храним роли как Map<PlayerId, RoleType>
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

    private Round(UUID gameId, String secretWord, Map<UUID, Role> roles) {
        this.gameId = gameId;;
        this.secretWord = secretWord;
        // ВАЖНО: Создаем неизменяемую копию карты, чтобы никто не мог изменить роли снаружи
        this.roles = Map.copyOf(roles);
    }

    public void addMine(Mine mine) {

        if (status == RoundStatus.ROUND_FINISHED) {
            throw new IllegalStateException("Нельзя добавлять мины после завершения раунда");
        }
        mines.add(mine);
    }

    public Role getRole(UUID playerId) {
        return roles.get(playerId);
    }

}