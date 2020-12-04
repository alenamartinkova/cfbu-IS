package vis.gateways;

import vis.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class UserGateway {
    static final ArrayList columns = new ArrayList<>(
            Arrays.asList("userID", "name", "sureName", "email", "login", "password", "isAdmin", "createdAt")
    );
    public UserGateway() {};

    public static ArrayList<User> fetch() throws SQLException {
        ResultSet rs = Table.conn.createStatement().executeQuery("SELECT * FROM MyUser");
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8)));
        }

        rs.close();
        return users;
    }

    public static Integer insert(User user) throws SQLException {
        Table t = new Table("MyUser", columns);
        String query = t.buildInsert(7, 1);

        PreparedStatement preparedQuery = null;
        int output = 0;
        try {
            preparedQuery = Table.conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedQuery.setString(1, user.getName());
            preparedQuery.setString(2, user.getSureName());
            preparedQuery.setString(3, user.getEmail());
            preparedQuery.setString(4, user.getLogin());
            preparedQuery.setString(5, user.getPassword());
            preparedQuery.setInt(6, user.getIsAdmin());
            preparedQuery.setTimestamp(7, user.getCreatedAt());

            output = preparedQuery.executeUpdate();

            try (ResultSet generatedKeys = preparedQuery.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    output = (int) generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static Integer update(User user) throws SQLException {
        int output = 0;
        Table t = new Table("MyUser", columns);
        String query = t.buildUpdate(1);

        PreparedStatement preparedQuery = null;
        try {
            preparedQuery = Table.conn.prepareStatement(query);
            preparedQuery.setString(1, user.getName());
            preparedQuery.setString(2, user.getSureName());
            preparedQuery.setString(3, user.getEmail());
            preparedQuery.setString(4, user.getLogin());
            preparedQuery.setString(5, user.getPassword());
            preparedQuery.setInt(6, user.getIsAdmin());
            preparedQuery.setTimestamp(7, user.getCreatedAt());
            preparedQuery.setInt(8, user.getUserID());

            output = preparedQuery.executeUpdate();
            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = Table.conn.prepareStatement("DELETE FROM User WHERE userID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }
}
