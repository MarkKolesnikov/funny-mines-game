package round.factory;

import player.domain.Player;
import round.domain.Role;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface RoleAssignmentService {

    Map<UUID, Role> assignRoles(List<Player> players);
}
