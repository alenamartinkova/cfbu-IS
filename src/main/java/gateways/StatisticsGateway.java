package gateways;

import DTO.StatisticsDTO;
import business.Player;
import business.Statistics;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class StatisticsGateway {
    static final ArrayList columns = new ArrayList<>(
            Arrays.asList("statsID", "playerID", "assists", "goals", "points")
    );
    public StatisticsGateway() {};

    public static ArrayList<StatisticsDTO> fetch() throws SQLException {
        ResultSet rs = Table.conn.createStatement().executeQuery("SELECT * FROM Stats");
        ArrayList<StatisticsDTO> stats = new ArrayList<>();
        while (rs.next()) {
            stats.add(new StatisticsDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
        }

        rs.close();
        return stats;
    }

    public static Integer insert(StatisticsDTO stats) throws SQLException {
        Table t = new Table("Stats", columns);
        String query = t.buildInsert(4, 1);

        PreparedStatement preparedQuery = null;
        int output = 0;
        try {
            preparedQuery = Table.conn.prepareStatement(query,
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

    public static Integer update(StatisticsDTO stats) throws SQLException {
        int output = 0;
        Table t = new Table("Stats", columns);
        String query = t.buildUpdate(1);

        PreparedStatement preparedQuery = null;
        try {
            preparedQuery = Table.conn.prepareStatement(query);
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


    public static Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = Table.conn.prepareStatement("DELETE FROM Stats WHERE statsID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }

    public static StatisticsDTO fetchByPlayerID(Integer pID) {
        String queryStr = "SELECT * FROM Stats WHERE playerID = ?";
        String val = pID.toString();
        StatisticsDTO statistics = new StatisticsDTO();
        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);
            query.setString(1, val);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                statistics = new StatisticsDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return statistics;
    }

    public static Integer updateBasedOnPlayerID(Integer pID, Integer sID) throws SQLException {
        int output = 0;
        Table t = new Table("Stats", columns);
        String query = t.buildUpdate(1);

        PreparedStatement preparedQuery = null;
        try {
            preparedQuery = Table.conn.prepareStatement(query);
            preparedQuery.setInt(1, pID);
            preparedQuery.setInt(2, 0);
            preparedQuery.setInt(3, 0);
            preparedQuery.setInt(4, 0);
            preparedQuery.setInt(5, sID);

            output = preparedQuery.executeUpdate();
            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

}
