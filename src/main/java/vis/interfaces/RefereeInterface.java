package vis.interfaces;

import vis.entities.Referee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RefereeInterface {
    Integer insert(Referee referee);
    Integer update(Referee referee);
    Integer delete(Integer id);
    ArrayList<Referee> fetch() throws SQLException;
    ArrayList<Referee> fetchByAttr(Object ... values);
}
