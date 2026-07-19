package round.factory;

import game.domain.Game;
import player.domain.Player;
import round.domain.Role;
import round.domain.Round;
import org.springframework.stereotype.Component;
import secret_word.service.WordService;

import java.util.*;

@Component
public class RoundFactory implements RoleAssignmentService {

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


    @Override
    public Map<UUID, Role> assignRoles(List<Player> players) {
        if (players == null || players.isEmpty()) {
            return Map.of();
        }

        Map<UUID, Role> roles = new HashMap<>();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (player == null) {
                throw new IllegalArgumentException("Игрок под индексом " + i + " равен null");
            }

            Role role = switch (i) {
                case 0 -> Role.GUESSER;
                case 1 -> Role.HINT_GIVER;
                default -> Role.MINER;
            };
            roles.put(player.getId(), role);
        }

        return roles;
    }

}


