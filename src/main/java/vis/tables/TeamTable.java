package vis.tables;
import vis.entities.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


public class TeamTable extends Table {
    public TeamTable() throws SQLException {
        super("Team");

        this.columns = new ArrayList<>(
                Arrays.asList("user_id", "username", "password", "first_name", "surname", "phone", "email")
        );
    };

    public ArrayList<Team> fetch() throws SQLException {
        ResultSet rs = this.conn.createStatement().executeQuery("SELECT * FROM TEAM");
        ArrayList<Team> teams = new ArrayList<Team>();
        while (rs.next()) {
            teams.add(new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getDate(6)));
        }

         rs.close();
         return teams;
    }

    public Integer insert(Object ... values) {
        try {
            Integer index = 1;
            PreparedStatement insertStatement = this.conn.prepareStatement("INSERT INTO TEAM VALUES (?, ?, ?, ?, ?)");
            for (Object o : values) {
                if (o instanceof String) {
                    insertStatement.setString(index, (String)o);
                    index++;
                }

                if (o instanceof Integer){
                    insertStatement.setInt(index, (Integer)o);
                    index++;
                }

                if (o instanceof Date){
                    insertStatement.setDate(index, (Date)o);
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
            PreparedStatement updateStatement = this.conn.prepareStatement("UPDATE TEAM SET leagueID = ?, name = ?, rank = ?, covid = ?, qurantinedFrom = ? WHERE teamID = ?");
            for (Object o : values) {
                if (o instanceof String) {
                    updateStatement.setString(index, (String)o);
                    index++;
                }

                if (o instanceof Integer){
                    updateStatement.setInt(index, (Integer) o);
                    index++;
                }

                if (o instanceof Date){
                    updateStatement.setDate(index, (Date)o);
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

    public ArrayList<Team> fetchByAttr(Object ... values) {
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");
        ArrayList<Team> team = new ArrayList<Team>();

        String queryStr = "SELECT * FROM TEAM WHERE ";
        for (int i = 0; i < values.length; i++) {
            if (i%2 == 0 && i != values.length - 2) queryStr += values[i] + " = ? AND ";
            else if (i%2 == 0 && i == values.length - 2) queryStr += values[i] + " = ?";
        }

        try {
            PreparedStatement query = conn.prepareStatement(queryStr);
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
                team.add(new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getDate(6)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return team;
    }

    public ArrayList<Team> searchByAttr(String val) {
        ArrayList<Team> team = new ArrayList<Team>();

        String queryStr = "SELECT * FROM TEAM WHERE TEAMID LIKE ? OR RANK LIKE ? OR NAME LIKE ? OR LEAGUEID LIKE ?";

        try {
            PreparedStatement query = this.conn.prepareStatement(queryStr);

            for (int i = 1; i <= 4; i++) {
                query.setString(i, "%" + val + "%");
            }

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                team.add(new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getDate(6)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return team;
    }


    public Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = this.conn.prepareStatement("DELETE FROM TEAM WHERE teamID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }
}
