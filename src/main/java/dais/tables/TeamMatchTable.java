package dais.tables;

import dais.entities.Statistics;
import dais.entities.Team;
import dais.entities.TeamMatch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamMatchTable {
    public TeamMatchTable(){};

    public ArrayList<TeamMatch> fetch() throws SQLException {
        ResultSet rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM TeamMatch");
        ArrayList<TeamMatch> teamMatches = new ArrayList<>();
        while (rs.next()) {
            teamMatches.add(new TeamMatch(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
        }

        rs.close();
        return teamMatches;
    }

    public ArrayList<TeamMatch> fetchByAttr(Object ... values) {
        ArrayList<TeamMatch> teamMatch = new ArrayList<>();
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");

        String queryStr = "SELECT * FROM TeamMatch WHERE ";
        for (int i = 0; i < values.length; i++) {
            if (i%2 == 0 && i != values.length - 2) queryStr += values[i] + " = ? AND ";
            else if (i%2 == 0 && i == values.length - 2) queryStr += values[i] + " = ?";
        }

        try {
            PreparedStatement query = TeamTable.conn.prepareStatement(queryStr);
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


    public Integer insert(Object ... values) {
        try {
            Integer index = 1;
            PreparedStatement insertStatement = TeamTable.conn.prepareStatement("INSERT INTO TeamMatch VALUES (?, ?, ?, ?, ?, ?, ?)");
            for (Object o : values) {
                if (o instanceof Integer){
                    insertStatement.setInt(index, (Integer)o);
                    index++;
                }
            }
            return insertStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }

    public Integer update(Integer id, Object ... values) {
        try {
            Integer index = 1;
            PreparedStatement updateStatement = TeamTable.conn.prepareStatement("UPDATE TeamMatch SET matchID = ?, firstTeamID = ?, secondTeamID = ?, firstRefereeID = ?, secondRefereeID = ?, firstTeamGoals = ?, secondTeamGoals = ? WHERE teamMatchID = ?");
            for (Object o : values) {
                if (o instanceof Integer){
                    updateStatement.setInt(index, (Integer) o);
                    index++;
                }
            }
            updateStatement.setInt(index, id);
            return updateStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }

    public Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM TeamMatch WHERE teamMatchID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }
}
