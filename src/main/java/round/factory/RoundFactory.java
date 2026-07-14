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

    private final WordService wordService;

    public RoundFactory(WordService wordService) {
        this.wordService = wordService;
    }

    public Round createRound(Game game) {

        List<Player> players = new ArrayList<>(game.getPlayers());
        Collections.shuffle(players);

        Map<UUID, Role> roles = assignRoles(players);

        String secretWord = wordService.generateWord();

        return Round.of(game.getId(), secretWord, roles);
    }


    private Map<UUID, Role> assignRoles(List<Player> players) {

        Map<UUID, Role> roles = new HashMap<>();
        for (Player player : players) {
            roles.put(player.getId(), Role.MINER);
        }
        roles.put(players.get(0).getId(), Role.GUESSER);
        if (players.size() >= 2) {
            roles.put(players.get(1).getId(), Role.HINT_GIVER);
        }
        return roles;
    }
}


