package vis;
import vis.business.Player;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Player p = new Player(1, 1, "Test", "test", "2002-01-01 00:00:00", 0, null, "nic", "R");
        p.insert();
    }
}