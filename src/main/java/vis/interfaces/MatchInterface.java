package vis.interfaces;

import vis.entities.Match;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MatchInterface {
    Integer insert(Match match);
    Integer update(Match match);
    Integer delete(Integer id);
    ArrayList<Match> fetch() throws SQLException;
    ArrayList<Match> fetchByAttr(Object ... values);
}
