package dais.tables;

import dais.entities.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerTable {
    private PlayerTable(){};

    public List<Player> fetch() throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM PLAYER");
        var players = new ArrayList<Player>();
        while (rs.next()) {
            players.add(new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
        }

        rs.close();
        return players;
    }

    public int insert(int id, String f_name, String l_name, int a, int g, int a_id, int t_id, int y) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("INSERT INTO PLAYER (player_id, first_name, last_name, assists, goals, address_id, team_id, year_born) VALUES (" + id + ", " + f_name + ", "+ l_name +", " + a +", " + g + ", " + a_id + ", " + t_id + ", " + y + " )");
    }

    public int update(int id, String f_name, String l_name, int a, int g, int a_id, int t_id, int y) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("UPDATE PLAYER SET first_name = " + f_name +", last_name = "+ l_name +", assists = "+ a +", goals = "+ g +", address_id = " + a_id +", team_id = " + t_id +", year_born = " + y +" WHERE player_id = "+ id +"");
    }

    public int delete(int column, int value) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("DELETE FROM PLAYER WHERE "+ column +"="+ value +"");
    }
}
