package vis.tables;

import vis.entities.Statistics;
import vis.interfaces.StatisticsInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class StatisticsTable extends Table implements StatisticsInterface {
    public StatisticsTable() throws SQLException {
        super("Stats");

        this.columns = new ArrayList<>(
                Arrays.asList("statsID", "playerID", "assists", "goals", "points")
        );
    };

    public ArrayList<Statistics> fetch() throws SQLException {
        ResultSet rs = this.conn.createStatement().executeQuery("SELECT * FROM Stats");
        ArrayList<Statistics> stats = new ArrayList<>();
        while (rs.next()) {
            stats.add(new Statistics(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
        }

        rs.close();
        return stats;
    }

    public ArrayList<Statistics> fetchByAttr(Object ... values) {
        ArrayList<Statistics> stats = new ArrayList<>();
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");

        String queryStr = "SELECT * FROM Stats WHERE ";
        for (int i = 0; i < values.length; i++) {
            if (i%2 == 0 && i != values.length - 2) queryStr += values[i] + " = ? AND ";
            else if (i%2 == 0 && i == values.length - 2) queryStr += values[i] + " = ?";
        }

        try {
            PreparedStatement query = this.conn.prepareStatement(queryStr);
            Integer index = 1;
            for (int i = 0; i < values.length; i++) {
                if (i % 2 != 0) {
                    if (values[i] instanceof Integer) {
                        query.setInt(index, (Integer) values[i]);
                        index++;
                    }
                }
            }

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                stats.add(new Statistics(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return stats;
    }


    public Integer insert(Statistics stats) {
        String query = this.buildInsert(4, 1);

        PreparedStatement preparedQuery = null;
        int output = 0;
        try {
            preparedQuery = this.conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedQuery.setInt(1, stats.getPlayerID());
            preparedQuery.setInt(2, stats.getAssists());
            preparedQuery.setInt(3, stats.getGoals());
            preparedQuery.setInt(4, stats.getPoints());

            output = preparedQuery.executeUpdate();

            try (ResultSet generatedKeys = preparedQuery.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    output = (int) generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating stats failed, no ID obtained.");
                }
            }

            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public Integer update(Statistics stats) {
        int output = 0;

        String query = this.buildUpdate(1);

        PreparedStatement preparedQuery = null;
        try {
            preparedQuery = this.conn.prepareStatement(query);
            preparedQuery.setInt(1, stats.getPlayerID());
            preparedQuery.setInt(2, stats.getAssists());
            preparedQuery.setInt(3, stats.getGoals());
            preparedQuery.setInt(4, stats.getPoints());
            preparedQuery.setInt(5, stats.getStatsID());

            output = preparedQuery.executeUpdate();
            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }


    public Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = this.conn.prepareStatement("DELETE FROM Stats WHERE statsID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }
}
