package vis.interfaces;

import vis.entities.Coach;

public interface CoachInterface {
    Integer insert(Coach coach);
    Integer update(Coach coach);
    Integer delete(Integer id);
}
