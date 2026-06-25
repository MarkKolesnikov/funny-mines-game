package model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static model.GameStatus.WAITING;

@Getter
public class Game {

    private final UUID id = UUID.randomUUID();

    @Setter
    private GameStatus status = WAITING;

    private final List<Player> players = new ArrayList<>();

    @Setter
    private Round currentRound;

    private final Map<UUID, Stats> leaderBoard = new HashMap<>();

    public void addPlayer(Player player) {
        players.add(player);
    }
}
