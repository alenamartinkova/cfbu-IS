package vis.tables;
import vis.entities.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class AddressTable {
    static final ArrayList columns = new ArrayList<>(
            Arrays.asList("addressID", "city", "street", "streetNumber")
    );
    
    public AddressTable() {};

    public static ArrayList<Address> fetch() throws SQLException {
        ResultSet rs = Table.conn.createStatement().executeQuery("SELECT * FROM ADDRESS");
        ArrayList<Address> addresses = new ArrayList<Address>();
        while (rs.next()) {
            addresses.add(new Address(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        }

        rs.close();
        return addresses;
    }

    public static ArrayList<Address> fetchByAttr(Object ... values) {
        ArrayList<Address> addr = new ArrayList<Address>();
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");

        String queryStr = "SELECT * FROM ADDRESS WHERE ";
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
                }
            }

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                addr.add(new Address(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return addr;
    }


    public static Integer insert(Address address) throws SQLException {
        Table t = new Table("Address", columns);
        String query = t.buildInsert(3, 1);

        PreparedStatement preparedQuery = null;
        int output = 0;
        try {
            preparedQuery = Table.conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedQuery.setString(1, address.getCity());
            preparedQuery.setString(2, address.getStreet());
            preparedQuery.setInt(3, address.getStreetNumber());

            output = preparedQuery.executeUpdate();

            try (ResultSet generatedKeys = preparedQuery.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    output = (int) generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating address failed, no ID obtained.");
                }
            }

            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static Integer update(Address address) throws SQLException {
        int output = 0;

        Table t = new Table("Address", columns);
        String query = t.buildUpdate(1);

        PreparedStatement preparedQuery = null;
        try {
            preparedQuery = Table.conn.prepareStatement(query);
            preparedQuery.setString(1, address.getCity());
            preparedQuery.setString(2, address.getStreet());
            preparedQuery.setInt(3, address.getStreetNumber());
            preparedQuery.setInt(4, address.getId());

            output = preparedQuery.executeUpdate();
            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = Table.conn.prepareStatement("DELETE FROM ADDRESS WHERE addressID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }
}
