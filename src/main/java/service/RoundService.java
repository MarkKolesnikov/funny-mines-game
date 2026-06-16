package service;

import model.Game;
import model.Round;
import org.springframework.stereotype.Service;
import store.GameRepository;

import java.util.UUID;

@Service
public class RoundService {

    GameRepository gameRepository;

    public RoundService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Round createRound(UUID gameId) {
        Game game = gameRepository.findByIdOrThrow(gameId);

    }
}
