package org.alenamartinkova.dais.gateways;

import org.alenamartinkova.dais.DTO.TeamMatchDTO;

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

    public static ArrayList<TeamMatchDTO> fetch() throws SQLException {
        ResultSet rs = Table.conn.createStatement().executeQuery("SELECT * FROM TeamMatch");
        ArrayList<TeamMatchDTO> teamMatches = new ArrayList<>();
        while (rs.next()) {
            teamMatches.add(new TeamMatchDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
        }

        rs.close();
        return teamMatches;
    }

    public static Integer insert(TeamMatchDTO teamMatch) throws SQLException {
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

    public static Integer update(TeamMatchDTO teamMatch) throws SQLException {
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
            e.printStackTrace();
        }

        return -1;
    }

    public static TeamMatchDTO fetchByMatchID(Integer id) {
        String queryStr = "SELECT * FROM TeamMatch WHERE matchID = ?";
        String val = id.toString();

        TeamMatchDTO match = new TeamMatchDTO();
        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);
            query.setString(1, val);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                match = new TeamMatchDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return match;
    }

    public static ArrayList<TeamMatchDTO> fetchByTeamID(Integer id) {
        String queryStr = "SELECT * FROM TeamMatch WHERE firstTeamID = ? OR secondTeamID = ?";
        String val = id.toString();

        ArrayList<TeamMatchDTO> matches = new ArrayList<>();
        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);
            query.setString(1, val);
            query.setString(2, val);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                matches.add(new TeamMatchDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matches;
    }
}
