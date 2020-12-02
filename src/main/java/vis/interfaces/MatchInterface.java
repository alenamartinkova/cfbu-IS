package vis.interfaces;

import vis.entities.Match;

public interface MatchInterface {
    Integer insert(Match match);
    Integer update(Match match);
    Integer delete(Integer id);
}
