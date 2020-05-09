package dais.tables;

import dais.entities.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamTable {
    public TeamTable(){};
    public static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@dbsys.cs.vsb.cz:1521:oracle","mar0702", "R5ddxZ4NnO");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Team> fetch() throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TEAM");
        var teams = new ArrayList<Team>();
        while (rs.next()) {
             teams.add(new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)));
        }

         rs.close();
         return teams;
    }

    public Integer insert(Object ... values) {
        try {
            var index = 1;
            var insertStatement = TeamTable.conn.prepareStatement("INSERT INTO TEAM VALUES (?, ?, ?, ?)");
            for (Object o : values) {
                if (o instanceof String) {
                    insertStatement.setString(index, (String)o);
                    index++;
                }

                if (o instanceof Integer ){
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
            var index = 1;
            var updateStatement = TeamTable.conn.prepareStatement("UPDATE TEAM SET rank = ?, name = ?, league_id = ? WHERE team_id = ?");
            for (Object o : values) {
                if (o instanceof String) {
                    updateStatement.setString(index, (String)o);
                    index++;
                }

                if (o instanceof Integer ){
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

    public List<Team> fetchByAttr(Object ... values) {
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");
        var team = new ArrayList<Team>();

        var queryStr = "SELECT * FROM TEAM WHERE ";
        for (int i = 0; i < values.length; i++) {
            if (i%2 == 0 && i != values.length - 2) queryStr += values[i] + " = ? AND ";
            else if (i%2 == 0 && i == values.length - 2) queryStr += values[i] + " = ?";
        }

        try {
            var query = conn.prepareStatement(queryStr);
            var index = 1;
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
                team.add(new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return team;
    }


    public Integer delete(Integer id) {
        try {
            var deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM TEAM WHERE TEAM_ID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }

    public void teamTransfer(Integer team_id, Integer league_id) {
        try (
                CallableStatement statement = TeamTable.conn.prepareCall(" {call transferTeam(?, ?)}");
        ) {
            statement.setInt(1, team_id );
            statement.setInt(2, league_id );
            statement.execute();
            statement.close();
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
