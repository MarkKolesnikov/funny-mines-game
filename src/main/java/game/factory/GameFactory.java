package game.factory;

import game.domain.Game;
import player.domain.Player;
import org.springframework.stereotype.Component;
import player.service.PlayerService;

@Component
public class GameFactory {

    private final PlayerService playerService;

    public GameFactory(PlayerService playerService) {
        this.playerService = playerService;
    }

    public Game createGame(String login) {
        Player player = playerService.createPlayer(login);

        Game game = new Game();
        game.addPlayer(player);

        return game;
    }
}
