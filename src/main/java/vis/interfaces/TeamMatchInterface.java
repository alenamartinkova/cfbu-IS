package vis.interfaces;

import vis.entities.TeamMatch;

public interface TeamMatchInterface {
    Integer insert(TeamMatch teamMatch);
    Integer update(TeamMatch teamMatch);
    Integer delete(Integer id);
}
