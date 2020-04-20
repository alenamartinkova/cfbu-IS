package dais.tables;

import dais.entities.Team;

import java.sql.CallableStatement;
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

    public Integer insert(Integer id, Integer r, String n, Integer l_id) throws SQLException {
        return conn.createStatement().executeUpdate("INSERT INTO TEAM (team_id, rank, name, league_id) VALUES (" + id.toString() + "," + r.toString() +", "+ n +", " + l_id.toString() +")");
    }

    public Integer update(Integer id, Integer r, String n, Integer l_id) throws SQLException {
        return conn.createStatement().executeUpdate("UPDATE TEAM SET rank = "+ r.toString() +", name = " + n +", league_id = " + l_id.toString()+" WHERE team_id = " + id.toString() + "");
    }

    public Integer delete(String column, String value) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("DELETE FROM TEAM WHERE "+ column +"="+ value +"");
    }

    public Team teamDetail(Integer id) throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM TEAM WHERE team_id = "+ id.toString() +"");
        var team = new Team();
        while (rs.next()) {
            team = new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
        }

        rs.close();
        return team;
    }

    public void teamTransfer(Integer team_id, Integer league_id) {
        try (
                CallableStatement statement = TeamTable.conn.prepareCall(" {call transferTeam(?, ?)}");
        ) {
            statement.setInt(1, team_id );
            statement.setInt(2, league_id );
            statement.execute();
            statement.close();
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
