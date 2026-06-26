package round.factory;

import game.domain.Game;
import round.domain.Round;
import org.springframework.stereotype.Component;
import secret_word.service.WordService;

@Component
public class RoundFactory {

    WordService wordService;

    public RoundFactory(WordService wordService) {
        this.wordService = wordService;
    }

    private Round createRound(Game game) {
        int roundNumber = calculateRoundNumber(game);
        String secretWord =

    }


    private int calculateRoundNumber(Game game) {
        if(game.getCurrentRound() == null) {
            return 1;
        }
        return game.getCurrentRound().getRoundNumber() + 1;
    }
}


