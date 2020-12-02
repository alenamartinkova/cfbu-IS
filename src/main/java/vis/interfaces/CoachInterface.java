package vis.interfaces;

import vis.entities.Coach;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CoachInterface {
    Integer insert(Coach coach);
    Integer update(Coach coach);
    Integer delete(Integer id);
    ArrayList<Coach> fetch() throws SQLException;
    ArrayList<Coach> fetchByAttr(Object ... values);
}
