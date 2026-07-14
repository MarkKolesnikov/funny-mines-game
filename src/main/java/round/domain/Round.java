package round.domain;

import lombok.Getter;
import lombok.Setter;
import mine.domain.Mine;

import java.util.*;

public class Round {

    @Getter
    private final UUID gameId;

    @Getter
    private final String secretWord;
    private final Map<UUID, Role> roles;

    @Getter
    private RoundStatus status = RoundStatus.PLACING_MINES;

    @Setter
    private RoundResult result;

    private final List<Mine> mines = new ArrayList<>();

    private Round(UUID gameId, String secretWord, Map<UUID, Role> roles) {
        this.gameId = Objects.requireNonNull(gameId, "gameId обязателен");
        this.secretWord = Objects.requireNonNull(secretWord, "secretWord обязателен");
        this.roles = Map.copyOf(
                Objects.requireNonNull(roles, "roles обязательны")
        );
        if (this.roles.isEmpty()) {
            throw new IllegalArgumentException("В раунде должны быть роли хотя бы для одного игрока");
        }
    }

    public static Round of(UUID gameId, String secretWord, Map<UUID, Role> roles) {
        return new Round(gameId, secretWord, roles);
    }

    public void addMine(Mine mine) {

        if (status == RoundStatus.ROUND_FINISHED) {
            throw new IllegalStateException("Нельзя добавлять мины после завершения раунда");
        }
        mines.add(mine);
    }
}