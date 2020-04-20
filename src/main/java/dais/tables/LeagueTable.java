package dais.tables;
import dais.entities.League;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeagueTable {
    public LeagueTable(){};

    public List<League> fetch() throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM LEAGUE");
        var leagues = new ArrayList<League>();
        while (rs.next()) {
            leagues.add(new League(rs.getInt(1), rs.getInt(2), rs.getString(3)));
        }

        rs.close();
        return leagues;
    }

    public Integer insert(Integer id, Integer d, String n) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("INSERT INTO LEAGUE (league_id, division, name) VALUES (" + id.toString() + ", " + d.toString() + ", '"+ n +"')");
    }

    public Integer update(Integer id, Integer d, String n) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("UPDATE LEAGUE SET division = "+ d.toString() +", name = '" + n + "' WHERE league_id = " + id.toString() +"");
    }

    public Integer delete(String column, String value) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("DELETE FROM LEAGUE WHERE '"+ column +"'='"+ value +"'");
    }

    public League leagueDetail(Integer id) throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM LEAGUE WHERE league_id = "+ id.toString() + "");
        var league = new League();
        while (rs.next()) {
            league = new League(rs.getInt(1), rs.getInt(2), rs.getString(3));
        }

        rs.close();
        return league;
    }

    public void changeLeague(String name, Integer division, Integer old_league_id) {
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

