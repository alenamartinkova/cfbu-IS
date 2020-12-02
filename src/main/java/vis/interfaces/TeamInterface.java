package vis.interfaces;

import vis.entities.Team;

public interface TeamInterface {
    Integer insert(Team team);
    Integer update(Team team);
    Integer delete(Integer id);
}
