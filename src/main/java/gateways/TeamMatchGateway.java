package gateways;

import business.Match;
import business.TeamMatch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class TeamMatchGateway {
    static final ArrayList columns = new ArrayList<>(
            Arrays.asList("teamMatchID", "matchID", "firstTeamID", "secondTeamID", "firstRefereeID", "secondRefereeID", "firstTeamGoals", "secondTeamGoals")
    );
    public TeamMatchGateway(){};

    public static ArrayList<TeamMatch> fetch() throws SQLException {
        ResultSet rs = Table.conn.createStatement().executeQuery("SELECT * FROM TeamMatch");
        ArrayList<TeamMatch> teamMatches = new ArrayList<>();
        while (rs.next()) {
            teamMatches.add(new TeamMatch(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
        }

        rs.close();
        return teamMatches;
    }

    public static ArrayList<TeamMatch> fetchByAttr(Object ... values) {
        ArrayList<TeamMatch> teamMatch = new ArrayList<>();
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");

        String queryStr = "SELECT * FROM TeamMatch WHERE ";
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
                }
            }

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                teamMatch.add(new TeamMatch(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));

            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return teamMatch;
    }


    public static Integer insert(TeamMatch teamMatch) throws SQLException {
        Table t = new Table("TeamMatch", columns);
        String query = t.buildInsert(7, 1);

        PreparedStatement preparedQuery = null;
        int output = 0;
        try {
            preparedQuery = Table.conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedQuery.setInt(1, teamMatch.getMatchID());
            preparedQuery.setInt(2, teamMatch.getFirstTeamID());
            preparedQuery.setInt(3, teamMatch.getSecondTeamID());
            preparedQuery.setInt(4, teamMatch.getFirstRefereeID());
            preparedQuery.setInt(5, teamMatch.getSecondRefereeID());
            preparedQuery.setInt(6, teamMatch.getFirstTeamGoals());
            preparedQuery.setInt(7, teamMatch.getSecondTeamGoals());

            output = preparedQuery.executeUpdate();

            try (ResultSet generatedKeys = preparedQuery.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    output = (int) generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating team match conn failed, no ID obtained.");
                }
            }

            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static Integer update(TeamMatch teamMatch) throws SQLException {
        int output = 0;
        Table t = new Table("TeamMatch", columns);
        String query = t.buildUpdate(1);

        PreparedStatement preparedQuery = null;
        try {
            preparedQuery = Table.conn.prepareStatement(query);
            preparedQuery.setInt(1, teamMatch.getMatchID());
            preparedQuery.setInt(2, teamMatch.getFirstTeamID());
            preparedQuery.setInt(3, teamMatch.getSecondTeamID());
            preparedQuery.setInt(4, teamMatch.getFirstRefereeID());
            preparedQuery.setInt(5, teamMatch.getSecondRefereeID());
            preparedQuery.setInt(6, teamMatch.getFirstTeamGoals());
            preparedQuery.setInt(7, teamMatch.getSecondTeamGoals());
            preparedQuery.setInt(8, teamMatch.getTeamMatchID());

            output = preparedQuery.executeUpdate();
            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = Table.conn.prepareStatement("DELETE FROM TeamMatch WHERE teamMatchID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }

    public static TeamMatch fetchByID(Integer id) {
        String queryStr = "SELECT * FROM TeamMatch WHERE teamMatchID = ?";
        String val = id.toString();
        TeamMatch match = new TeamMatch();
        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);
            query.setString(1, val);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                match = new TeamMatch(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return match;
    }

    public static TeamMatch fetchByMatchID(Integer id) {
        String queryStr = "SELECT * FROM TeamMatch WHERE matchID = ?";
        String val = id.toString();

        TeamMatch match = new TeamMatch();
        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);
            query.setString(1, val);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                match = new TeamMatch(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return match;
    }
}
