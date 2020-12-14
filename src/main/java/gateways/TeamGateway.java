package gateways;

import DTO.TeamDTO;
import business.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


public class TeamGateway {
    static final ArrayList columns = new ArrayList<>(
            Arrays.asList("teamID", "leagueID", "name", "rank", "covid", "quarantinedFrom")
    );
    public TeamGateway() {};

    public static ArrayList<TeamDTO> fetch() throws SQLException {
        ResultSet rs = Table.conn.createStatement().executeQuery("SELECT * FROM TEAM");
        ArrayList<TeamDTO> teams = new ArrayList<>();
        while (rs.next()) {
            teams.add(new TeamDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6)));
        }

         rs.close();
         return teams;
    }

    public static Integer insert(TeamDTO team) throws SQLException {
        Table t = new Table("Team", columns);
        String query = t.buildInsert(5, 1);

        PreparedStatement preparedQuery = null;
        int output = 0;
        try {
            preparedQuery = Table.conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedQuery.setInt(1, team.getLeagueID());
            preparedQuery.setString(2, team.getName());
            preparedQuery.setInt(3, team.getRank());
            preparedQuery.setInt(4, team.getCovid());
            preparedQuery.setTimestamp(5, team.getQuarantinedFrom());

            output = preparedQuery.executeUpdate();

            try (ResultSet generatedKeys = preparedQuery.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    output = (int) generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating team failed, no ID obtained.");
                }
            }

            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static Integer update(TeamDTO team) throws SQLException {
        int output = 0;
        Table t = new Table("Team", columns);
        String query = t.buildUpdate(1);

        PreparedStatement preparedQuery = null;
        try {
            preparedQuery = Table.conn.prepareStatement(query);
            preparedQuery.setInt(1, team.getLeagueID());
            preparedQuery.setString(2, team.getName());
            preparedQuery.setInt(3, team.getRank());
            preparedQuery.setInt(4, team.getCovid());
            preparedQuery.setTimestamp(5, team.getQuarantinedFrom());
            preparedQuery.setInt(6, team.getId());

            output = preparedQuery.executeUpdate();
            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = Table.conn.prepareStatement("DELETE FROM TEAM WHERE teamID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }

    public static TeamDTO fetchByID(Integer id) {
        String queryStr = "SELECT * FROM Team WHERE teamID = ?";
        String val = id.toString();
        TeamDTO team = new TeamDTO();
        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);
            query.setString(1, val);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                team = new TeamDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return team;
    }

    public static TeamDTO fetchByName(String name) {
        String queryStr = "SELECT * FROM Team WHERE name = ?";
        TeamDTO team = new TeamDTO();
        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);
            query.setString(1, name);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                team = new TeamDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return team;
    }
}
