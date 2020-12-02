package vis.interfaces;

import vis.entities.Player;

import java.util.ArrayList;

public interface PlayerInterface {
    Integer insert(Player player);
    Integer update(Player player);
    Integer delete(Integer id);
    ArrayList<Player> searchByAttr(String val);
}
