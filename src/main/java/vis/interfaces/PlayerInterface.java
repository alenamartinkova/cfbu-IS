package vis.interfaces;

import vis.entities.Player;

public interface PlayerInterface {
    Integer insert(Player player);
    Integer update(Player player);
    Integer delete(Integer id);
}
