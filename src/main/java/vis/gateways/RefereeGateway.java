package vis.gateways;

import vis.entities.Referee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RefereeGateway {
    static final ArrayList columns = new ArrayList<>(
            Arrays.asList("refereeID", "name", "sureName", "email", "dateOfBirth")
    );
    public RefereeGateway() {};

    public static ArrayList<Referee> fetch() throws SQLException {
        ResultSet rs = Table.conn.createStatement().executeQuery("SELECT * FROM Referee");
        ArrayList<Referee> referees = new ArrayList<>();
        while (rs.next()) {
            referees.add(new Referee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }

        rs.close();
        return referees;
    }

    public static ArrayList<Referee> fetchByAttr(Object ... values) {
        ArrayList<Referee> referee = new ArrayList<>();
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");

        String queryStr = "SELECT * FROM Referee WHERE ";
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

                    if (values[i] instanceof Date) {
                        query.setDate(index, (Date) values[i]);
                        index++;
                    }
                }
            }

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                referee.add(new Referee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return referee;
    }

    public static Integer insert(Referee referee) throws SQLException {
        Table t = new Table("Referee", columns);
        String query = t.buildInsert(4, 1);

        PreparedStatement preparedQuery = null;
        int output = 0;
        try {
            preparedQuery = Table.conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedQuery.setString(1, referee.getName());
            preparedQuery.setString(2, referee.getSureName());
            preparedQuery.setString(3, referee.getEmail());
            preparedQuery.setTimestamp(4, referee.getDateOfBirth());

            output = preparedQuery.executeUpdate();

            try (ResultSet generatedKeys = preparedQuery.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    output = (int) generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating referee failed, no ID obtained.");
                }
            }

            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static Integer update(Referee referee) throws SQLException {
        int output = 0;
        Table t = new Table("Referee", columns);
        String query = t.buildUpdate(1);

        PreparedStatement preparedQuery = null;
        try {
            preparedQuery = Table.conn.prepareStatement(query);
            preparedQuery.setString(1, referee.getName());
            preparedQuery.setString(2, referee.getSureName());
            preparedQuery.setString(3, referee.getEmail());
            preparedQuery.setTimestamp(4, referee.getDateOfBirth());
            preparedQuery.setInt(5, referee.getRefereeID());

            output = preparedQuery.executeUpdate();
            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = Table.conn.prepareStatement("DELETE FROM Referee WHERE refereeID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }
}
