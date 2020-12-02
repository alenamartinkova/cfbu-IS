package vis.interfaces;

import vis.entities.League;

public interface LeagueInterface {
    Integer insert(League league);
    Integer update(League league);
    Integer delete(Integer id);
}
