package dais.tables;

import dais.entities.ReprePlayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReprePlayerTable {
    public ReprePlayerTable(){};
    public List<ReprePlayer> fetch() throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM REPRE_PLAYER");
        var players = new ArrayList<ReprePlayer>();
        while (rs.next()) {
            players.add(new ReprePlayer(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
        }

        rs.close();
        return players;
    }

    public Integer insert(Integer y, Integer p_id, Integer r_id) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("INSERT INTO REPRE_PLAYER (year, player_id, repre_id) VALUES (" + y.toString() + ", " + p_id.toString() + ", "+ r_id.toString() +")");
    }

    public Integer update(Integer y, Integer p_id, Integer r_id) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("UPDATE REPRE_PLAYER SET year = "+ y.toString() +" WHERE player_id = "+ p_id.toString() +" AND repre_id = " + r_id.toString() +"");
    }

    public Integer delete(String column, String value) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("DELETE FROM REPRE_PLAYER WHERE "+ column +"="+ value +"");
    }
}
