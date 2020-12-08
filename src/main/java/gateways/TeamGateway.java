package gateways;

import business.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


public class TeamGateway {
    static final ArrayList columns = new ArrayList<>(
            Arrays.asList("teamID", "leagueID", "name", "rank", "covid", "quarantinedFrom")
    );
    public TeamGateway() {};

    public static ArrayList<Team> fetch() throws SQLException {
        ResultSet rs = Table.conn.createStatement().executeQuery("SELECT * FROM TEAM");
        ArrayList<Team> teams = new ArrayList<Team>();
        while (rs.next()) {
            teams.add(new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6)));
        }

         rs.close();
         return teams;
    }

    public static Integer insert(Team team) throws SQLException {
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

    public static Integer update(Team team) throws SQLException {
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


    public static ArrayList<Team> fetchByAttr(Object ... values) {
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");
        ArrayList<Team> team = new ArrayList<Team>();

        String queryStr = "SELECT * FROM TEAM WHERE ";
        for (int i = 0; i < values.length; i++) {
            if (i%2 == 0 && i != values.length - 2) queryStr += values[i] + " = ? AND ";
            else if (i%2 == 0 && i == values.length - 2) queryStr += values[i] + " = ?";
        }

        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);
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

                    if (values[i] instanceof Date){
                        query.setDate(index, (Date)values[i]);
                        index++;
                    }
                }
            }

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                team.add(new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return team;
    }

    public static ArrayList<Team> searchByAttr(String val) {
        ArrayList<Team> team = new ArrayList<Team>();

        String queryStr = "SELECT * FROM TEAM WHERE TEAMID LIKE ? OR RANK LIKE ? OR NAME LIKE ? OR LEAGUEID LIKE ?";

        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);

            for (int i = 1; i <= 4; i++) {
                query.setString(i, "%" + val + "%");
            }

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                team.add(new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return team;
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

    public static Team fetchByID(Integer id) {
        String queryStr = "SELECT * FROM Team WHERE teamID = ?";
        String val = id.toString();
        Team team = new Team();
        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);
            query.setString(1, val);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                team = new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return team;
    }

    public static Team fetchByName(String name) {
        String queryStr = "SELECT * FROM Team WHERE name = ?";
        Team team = new Team();
        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);
            query.setString(1, name);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                team = new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return team;
    }
}
