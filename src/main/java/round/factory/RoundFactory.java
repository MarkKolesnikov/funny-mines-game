package round.factory;

import game.domain.Game;
import player.domain.Player;
import round.domain.Role;
import round.domain.Round;
import org.springframework.stereotype.Component;
import secret_word.service.WordService;

import java.util.*;

@Component
public class RoundFactory {

    private  final WordService wordService;

    public RoundFactory(WordService wordService) {
        this.wordService = wordService;
    }

    private Round createRound(Game game) {
        if(game.getPlayers() == null || game.getPlayers().isEmpty()) {
            throw new IllegalArgumentException("В игре нет игроков для распределения ролей");
        }

        List<Player> players = new ArrayList<>(game.getPlayers());
        Collections.shuffle(players);

        int roundNumber = calculateRoundNumber(game);
        Map<UUID, Role> roles = assignRoles(players);

    }


    private static Map<UUID, Role> assignRoles(List<Player> players) {
        Map<UUID, Role> roles = new HashMap<>();

        if (players.isEmpty()) {
            return roles;
        }

        for (Player player : players) {
            roles.put(player.getId(), Role.MINER);
        }

        roles.put(players.get(0).getId(), Role.GUESSER);

        if (players.size() > 1) {
            roles.put(players.get(1).getId(), Role.HINT_GIVER);
        }

        return roles;
    }


    private int calculateRoundNumber(Game game) {
        if(game.getCurrentRound() == null) {
            return 1;
        }
        return game.getCurrentRound().getRoundNumber() + 1;
    }
}


