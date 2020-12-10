package business;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MyList {
    public ArrayList<Player> getPlayerList() throws SQLException;
}
