package service;

import exceptions.GameNotFoundException;
import exceptions.GameStatusException;
import model.*;
import org.springframework.stereotype.Service;
import store.GameRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoundService {

    private final GameRepository gameRepository;

    public RoundService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Round createRound(UUID gameId) {
        Game game = gameRepository.findById(gameId)
                        .orElseThrow(() -> new GameNotFoundException(gameId));

        validateGameStatus(game);

        // TODO лучше присваивать роли через обычный индекс

    }

    //Если роли должны быть предсказуемыми для тестов,
    // случайность стоит делать отдельно, а не прямо внутри всей логики
    //assgin приватный нужен
    private Map<UUID, Role> assignRoles(List<Player> players) {

        List<Role> roles = new ArrayList<>(List.of(Role.GUESSER, Role.MINER, Role.HINT_GIVER));

        Collections.shuffle(roles);


    }

    private List<Role> shuffleRoles(List<Role> roles) {

        return roles.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Collections.shuffle(list);
                            return list;
                        }
                ));
    }


    private void validateGameStatus(Game game) {
        if (game.getStatus() != GameStatus.IN_PROCESSING) {
            throw new GameStatusException(game.getStatus());
        }
    }
}
