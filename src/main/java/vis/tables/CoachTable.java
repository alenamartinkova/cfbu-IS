package vis.tables;

import vis.entities.Coach;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CoachTable extends Table{
    public CoachTable() throws SQLException {
        super("Coach");

        this.columns = new ArrayList<>(
                Arrays.asList("memberID", "teamID", "name", "sureName", "dateOfBirth", "covid", "quarantinedFrom", "email", "license")
        );
    };

    public ArrayList<Coach> fetch() throws SQLException {
        ResultSet rs = this.conn.createStatement().executeQuery("SELECT * FROM COACH");
        ArrayList<Coach> coaches = new ArrayList<>();
        while (rs.next()) {
            coaches.add(new Coach(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9)));
        }

        rs.close();
        return coaches;
    }

    public ArrayList<Coach> fetchByAttr(Object ... values) {
        ArrayList<Coach> coach = new ArrayList<>();
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");

        String queryStr = "SELECT * FROM Coach WHERE ";
        for (int i = 0; i < values.length; i++) {
            if (i%2 == 0 && i != values.length - 2) queryStr += values[i] + " = ? AND ";
            else if (i%2 == 0 && i == values.length - 2) queryStr += values[i] + " = ?";
        }

        try {
            PreparedStatement query = this.conn.prepareStatement(queryStr);
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

                    if (values[i] instanceof Date) {
                        query.setDate(index, (Date) values[i]);
                        index++;
                    }
                }
            }

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                coach.add(new Coach(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return coach;
    }

    public Integer insert(Coach coach) {
        String query = this.buildInsert(8, 1);

        PreparedStatement preparedQuery = null;
        int output = 0;
        try {
            preparedQuery = this.conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedQuery.setInt(1, coach.getTeamID());
            preparedQuery.setString(2, coach.getName());
            preparedQuery.setString(3, coach.getSureName());
            preparedQuery.setTimestamp(4, coach.getDateOfBirth());
            preparedQuery.setInt(5, coach.getCovid());
            preparedQuery.setTimestamp(6, coach.getQuarantinedFrom());
            preparedQuery.setString(7, coach.getEmail());
            preparedQuery.setString(8, coach.getLicense());

            output = preparedQuery.executeUpdate();

            try (ResultSet generatedKeys = preparedQuery.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    output = (int) generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating coach failed, no ID obtained.");
                }
            }

            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public int update(Coach coach) {
        int output = 0;

        String query = this.buildUpdate(1);

        PreparedStatement preparedQuery = null;
        try {
            preparedQuery = this.conn.prepareStatement(query);
            preparedQuery.setInt(1, coach.getTeamID());
            preparedQuery.setString(2, coach.getName());
            preparedQuery.setString(3, coach.getSureName());
            preparedQuery.setTimestamp(4, coach.getDateOfBirth());
            preparedQuery.setInt(5, coach.getCovid());
            preparedQuery.setTimestamp(6, coach.getQuarantinedFrom());
            preparedQuery.setString(7, coach.getEmail());
            preparedQuery.setString(8, coach.getLicense());
            preparedQuery.setInt(9, coach.getId());

            output = preparedQuery.executeUpdate();
            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = this.conn.prepareStatement("DELETE FROM Coach WHERE coachID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }
}
