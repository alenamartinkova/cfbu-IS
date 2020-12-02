package vis.interfaces;

import vis.entities.TeamMatch;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeamMatchInterface {
    Integer insert(TeamMatch teamMatch);
    Integer update(TeamMatch teamMatch);
    Integer delete(Integer id);
    ArrayList<TeamMatch> fetch() throws SQLException;
    ArrayList<TeamMatch> fetchByAttr(Object ... values);
}
