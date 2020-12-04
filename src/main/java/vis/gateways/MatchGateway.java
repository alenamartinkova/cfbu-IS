package vis.gateways;
import vis.entities.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MatchGateway {
    static final ArrayList columns = new ArrayList<>(
            Arrays.asList("matchID", "postponed", "date", "pitchID")
    );
    public MatchGateway() {};

    public static ArrayList<Match> fetch() throws SQLException {
        ResultSet rs = Table.conn.createStatement().executeQuery("SELECT * FROM Match");
        ArrayList<Match> matches = new ArrayList<>();
        while (rs.next()) {
            matches.add(new Match(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)));
        }

        rs.close();
        return matches;
    }

    public static ArrayList<Match> fetchByAttr(Object ... values) {
        ArrayList<Match> match = new ArrayList<>();
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");

        String queryStr = "SELECT * FROM Match WHERE ";
        for (int i = 0; i < values.length; i++) {
            if (i%2 == 0 && i != values.length - 2) queryStr += values[i] + " = ? AND ";
            else if (i%2 == 0 && i == values.length - 2) queryStr += values[i] + " = ?";
        }

        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);
            Integer index = 1;
            for (int i = 0; i < values.length; i++) {
                if (i % 2 != 0) {
                    if (values[i] instanceof Integer) {
                        query.setInt(index, (Integer) values[i]);
                        index++;
                    }

                    if (values[i] instanceof Date) {
                        query.setDate(index, (Date) values[i]);
                        index++;
                    }
                }
            }

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                match.add(new Match(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return match;
    }


    public static Integer insert(Match match) throws SQLException {
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

    public static Integer update(Match match) throws SQLException {
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
}
