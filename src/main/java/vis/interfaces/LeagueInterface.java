package vis.interfaces;

import vis.entities.League;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LeagueInterface {
    Integer insert(League league);
    Integer update(League league);
    Integer delete(Integer id);
    ArrayList<League> fetch() throws SQLException;
    ArrayList<League> fetchByAttr(Object ... values);
}
