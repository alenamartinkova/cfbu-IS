package vis.interfaces;

import vis.entities.Statistics;

public interface StatisticsInterface {
    Integer insert(Statistics stats);
    Integer update(Statistics stats);
    Integer delete(Integer id);
}
