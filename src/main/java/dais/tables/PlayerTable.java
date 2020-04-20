package dais.tables;

import dais.entities.Player;

import java.sql.CallableStatement;
import java.sql.ResultSet;
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

    public Player playerDetail(int id) throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM PLAYER WHERE player_id = "+ id +"");
        var player = new Player();
        while (rs.next()) {
            player = new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
        }

        rs.close();
        return player;
    }

    public void playerTransfer(int player_id, int team_id) {
        try (
                CallableStatement statement = TeamTable.conn.prepareCall(" {call playerTransfer(?, ?)}");
        ) {
            statement.setInt(1, player_id );
            statement.setInt(2, team_id );
            statement.execute();
            statement.close();
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Player> repreTeammates(int player_id) {
        var players = new ArrayList<Player>();

        try (
                CallableStatement statement = TeamTable.conn.prepareCall(" {call repreTeammates(?)}");
        ) {
            statement.setInt(1, player_id );
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                players.add(new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
            }
            statement.close();
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return players;
    }
}
