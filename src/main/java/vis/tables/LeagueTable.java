package vis.tables;
import vis.entities.League;
import vis.entities.Player;
import vis.interfaces.LeagueInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class LeagueTable extends Table implements LeagueInterface {
    public LeagueTable() throws SQLException {
        super("League");

        this.columns = new ArrayList<>(
                Arrays.asList("leagueID", "name", "category")
        );
    };

    public ArrayList<League> fetch() throws SQLException {
        ResultSet rs = this.conn.createStatement().executeQuery("SELECT * FROM LEAGUE");
        ArrayList<League> leagues = new ArrayList<League>();
        while (rs.next()) {
            leagues.add(new League(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }

        rs.close();
        return leagues;
    }

    public Integer insert(League league) {
        String query = this.buildInsert(2, 1);

        PreparedStatement preparedQuery = null;
        int output = 0;
        try {
            preparedQuery = this.conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedQuery.setString(1, league.getName());
            preparedQuery.setInt(2, league.getCategory());

            output = preparedQuery.executeUpdate();

            try (ResultSet generatedKeys = preparedQuery.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    output = (int) generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating league failed, no ID obtained.");
                }
            }

            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public Integer update(League league) {
        int output = 0;

        String query = this.buildUpdate(1);

        PreparedStatement preparedQuery = null;
        try {
            preparedQuery = this.conn.prepareStatement(query);
            preparedQuery.setString(1, league.getName());
            preparedQuery.setInt(2, league.getCategory());
            preparedQuery.setInt(3, league.getId());

            output = preparedQuery.executeUpdate();
            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public ArrayList<League> fetchByAttr(Object ... values) {
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");
        ArrayList<League> league = new ArrayList<League>();

        String queryStr = "SELECT * FROM LEAGUE WHERE ";
        for (int i = 0; i < values.length; i++) {
            if (i%2 == 0 && i != values.length - 2) queryStr += values[i] + " = ? AND ";
            else if (i%2 == 0 && i == values.length - 2) queryStr += values[i] + " = ?";
        }

        try {
            PreparedStatement query = this.conn.prepareStatement(queryStr);
            Integer index = 1;
            for (int i = 0; i < values.length; i++) {
                if (i % 2 != 0) {
                    if (values[i] instanceof String) {
                        query.setString(index, (String) values[i]);
                        index++;
                    }
                    if (values[i] instanceof Integer) {
                        query.setInt(index, (Integer) values[i]);
                        index++;
                    }
                }
            }

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                league.add(new League(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return league;
    }

    public Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = this.conn.prepareStatement("DELETE FROM LEAGUE WHERE leagueID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }
}

