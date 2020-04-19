package dais.tables;

import dais.entities.Player;
import dais.entities.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamTable {
    private TeamTable(){};
    public static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@dbsys.cs.vsb.cz:1521:oracle","mar0702", "R5ddxZ4NnO");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Team> fetch() throws SQLException {
        var rs = conn.createStatement().executeQuery("SELECT * FROM TEAM");
        var teams = new ArrayList<Team>();
         while (rs.next()) {
             teams.add(new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)));
         }

         rs.close();
         return teams;
    }

    public int insert(int id, int r, String n, int l_id) throws SQLException {
        return conn.createStatement().executeUpdate("INSERT INTO TEAM (team_id, rank, name, league_id) VALUES (" + id + "," + r +", "+ n +", " + l_id +")");
    }

    public int update(int id, int r, String n, int l_id) throws SQLException {
        return conn.createStatement().executeUpdate("UPDATE TEAM SET rank = "+ r +", name = " + n +", league_id = " + l_id +" WHERE team_id = " + id + "");
    }

    public int delete(int column, int value) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("DELETE FROM TEAM WHERE "+ column +"="+ value +"");
    }

    public Team teamDetail(int id) throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM TEAM WHERE team_id = "+ id +"");
        var team = new Team();
        while (rs.next()) {
            team = new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
        }

        rs.close();
        return team;
    }

}
