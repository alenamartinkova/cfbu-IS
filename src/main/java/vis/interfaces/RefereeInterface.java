package vis.interfaces;

import vis.entities.Referee;

public interface RefereeInterface {
    Integer insert(Referee referee);
    Integer update(Referee referee);
    Integer delete(Integer id);
}
