package vis.interfaces;

import vis.entities.Team;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeamInterface {
    Integer insert(Team team);
    Integer update(Team team);
    Integer delete(Integer id);
    ArrayList<Team> fetch() throws SQLException;
    ArrayList<Team> fetchByAttr(Object ... values);
    ArrayList<Team> searchByAttr(String val);
    Team fetchByID(Integer id);
}
