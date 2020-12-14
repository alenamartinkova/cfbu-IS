package gateways;
import DTO.MatchDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MatchGateway {
    static final ArrayList columns = new ArrayList<>(
            Arrays.asList("matchID", "postponed", "date", "pitchID")
    );
    public MatchGateway() {};

    public static ArrayList<MatchDTO> fetch() throws SQLException {
        ResultSet rs = Table.conn.createStatement().executeQuery("SELECT * FROM Match");
        ArrayList<MatchDTO> matches = new ArrayList<>();
        while (rs.next()) {
            matches.add(new MatchDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)));
        }

        rs.close();
        return matches;
    }

    public static Integer insert(MatchDTO match) throws SQLException {
        Table t = new Table("Match", columns);
        String query = t.buildInsert(3, 1);

        PreparedStatement preparedQuery = null;
        int output = 0;
        try {
            preparedQuery = Table.conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedQuery.setInt(1, match.getPostponed());
            preparedQuery.setTimestamp(2, match.getDate());
            preparedQuery.setInt(3, match.getPitchID());

            output = preparedQuery.executeUpdate();

            try (ResultSet generatedKeys = preparedQuery.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    output = (int) generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating match failed, no ID obtained.");
                }
            }

            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static Integer update(MatchDTO match) throws SQLException {
        int output = 0;
        Table t = new Table("Match", columns);
        String query = t.buildUpdate(1);

        PreparedStatement preparedQuery = null;
        try {
            preparedQuery = Table.conn.prepareStatement(query);
            preparedQuery.setInt(1, match.getPostponed());
            preparedQuery.setTimestamp(2, match.getDate());
            preparedQuery.setInt(3, match.getPitchID());
            preparedQuery.setInt(4, match.getMatchID());

            output = preparedQuery.executeUpdate();
            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = Table.conn.prepareStatement("DELETE FROM MATCH WHERE matchID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }

    public static MatchDTO fetchByID(Integer id) {
        String queryStr = "SELECT * FROM Match WHERE matchID = ?";
        String val = id.toString();
        MatchDTO match = new MatchDTO();
        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);
            query.setString(1, val);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                match = new MatchDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return match;
    }
}
