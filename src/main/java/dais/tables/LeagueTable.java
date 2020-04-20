package dais.tables;
import dais.entities.League;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeagueTable {
    private LeagueTable(){};

    public List<League> fetch() throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM LEAGUE");
        var leagues = new ArrayList<League>();
        while (rs.next()) {
            leagues.add(new League(rs.getInt(1), rs.getInt(2), rs.getString(3)));
        }

        rs.close();
        return leagues;
    }

    public int insert(int id, int d, String n) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("INSERT INTO LEAGUE (league_id, division, name) VALUES (" + id + ", " + d + ", "+ n +")");
    }

    public int update(int id, int d, String n) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("UPDATE LEAGUE SET division = "+ d +", name = " + n + " WHERE league_id = " + id +"");
    }

    public int delete(int column, int value) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("DELETE FROM LEAGUE WHERE "+ column +"="+ value +"");
    }

    public League leagueDetail(int id) throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM LEAGUE WHERE league_id = "+ id + "");
        var league = new League();
        while (rs.next()) {
            league = new League(rs.getInt(1), rs.getInt(2), rs.getString(3));
        }

        rs.close();
        return league;
    }

    public void changeLeague(String name, int division, int old_league_id) {
        try (
                CallableStatement statement = TeamTable.conn.prepareCall(" {call changeLeague(?, ?, ?)}");
        ) {
            statement.setString(1, name );
            statement.setInt(2, division );
            statement.setInt(3, old_league_id );
            statement.execute();
            statement.close();
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

