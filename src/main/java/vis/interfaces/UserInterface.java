package vis.interfaces;

import vis.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserInterface {
    Integer insert(User user);
    Integer update(User user);
    Integer delete(Integer id);
    ArrayList<User> fetch() throws SQLException;

}
