package round.service;

import exceptions.GameStatusException;
import game.domain.Game;
import game.domain.GameStatus;
import org.springframework.stereotype.Service;
import round.domain.Round;
import game.service.GameService;
import round.factory.RoundFactory;

@Service
public class RoundService {

    GameService gameService;
    RoundFactory roundFactory;

    public RoundService(GameService gameService, RoundFactory roundFactory) {
        this.gameService = gameService;
        this.roundFactory = roundFactory;
    }

    public Round startRound(Game game) {


    }

    private void validateGameStatus(Game game) {
        if (game.getStatus() != GameStatus.IN_PROCESSING) {
            throw new GameStatusException(game.getStatus());
        }
    }
}
