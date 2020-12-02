package vis.interfaces;

import vis.entities.Statistics;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StatisticsInterface {
    Integer insert(Statistics stats);
    Integer update(Statistics stats);
    Integer delete(Integer id);
    ArrayList<Statistics> fetch() throws SQLException;
    ArrayList<Statistics> fetchByAttr(Object ... values);
}
