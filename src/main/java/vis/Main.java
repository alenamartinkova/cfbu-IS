package vis;

import vis.entities.Player;
import vis.tables.PlayerTable;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        PlayerTable pt = new PlayerTable();
        pt.delete("name", "Test");
    }
}