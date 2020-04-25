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

    public Integer insert(Object ... values) throws SQLException {
        try {
            var index = 1;
            var insertStatement = TeamTable.conn.prepareStatement("INSERT INTO LEAGUE VALUES (?, ?, ?)");
            for (Object o : values) {
                if (o instanceof String) {
                   insertStatement.setString(index, (String)o);
                    index++;
                }

                if (o instanceof Integer ){
                    insertStatement.setInt(index, (Integer)o);
                    index++;
                }
            }
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 1;
    }

    public Integer update(Integer id, Object ... values) throws SQLException {
        try {
            var index = 1;
            var updateStatement = TeamTable.conn.prepareStatement("UPDATE LEAGUE SET division = ?, name = ? WHERE league_id = ?");
            for (Object o : values) {
                if (o instanceof String) {
                   updateStatement.setString(index, (String)o);
                   index++;
                }

                if (o instanceof Integer ){
                    updateStatement.setInt(index, (Integer)o);
                    index++;
                }
            }
            updateStatement.setInt(index, id);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 1;
    }

    public Integer delete(Object ... values) throws SQLException {
        try {
            var index = 1;
            var deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM LEAGUE WHERE ? = ?");
            for (Object o : values) {
                if (o instanceof String) {
                    deleteStatement.setString(index, (String)o);
                    index++;
                }

                if (o instanceof Integer){
                    deleteStatement.setInt(index, (Integer)o);
                    index++;
                }
            }
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 1;
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

