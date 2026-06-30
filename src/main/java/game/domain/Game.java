package game.domain;

import lombok.Getter;
import lombok.Setter;
import player.domain.Player;
import round.domain.Round;
import stats.domain.Stats;

import java.util.*;

import static game.domain.GameStatus.WAITING;

@Getter
public class Game {

    private final UUID id = UUID.randomUUID();

    private int nextGuesserIndex;

    @Setter
    private GameStatus status = WAITING;

    private final List<Player> players = new ArrayList<>();

    @Setter
    private Round currentRound;

    private final Map<UUID, Stats> leaderBoard = new HashMap<>();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public UUID getNextGuesserId() {
        UUID guesserId = players.get(nextGuesserIndex).getId();
        nextGuesserIndex = (nextGuesserIndex + 1) % players.size();
        return guesserId;
    }
}
