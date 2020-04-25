package dais.tables;

import dais.entities.Address;
import dais.entities.League;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressTable {
    public AddressTable(){};

    public List<Address> fetch() throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM ADDRESS");
        var addresses = new ArrayList<Address>();
        while (rs.next()) {
            addresses.add(new Address(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
        }

        rs.close();
        return addresses;
    }

    public List<Address> fetchByAttr(Object ... values) {
        var addr = new ArrayList<Address>();
        if (values.length % 2 != 0) throw new IllegalArgumentException("There must be even number of arguments.");

        var queryStr = "SELECT * FROM ADDRESS WHERE ";
        for (int i = 0; i < values.length; i++) {
            if (i%2 == 0 && i != values.length - 2) queryStr += values[i] + " = ? AND ";
            else if (i%2 == 0 && i == values.length - 2) queryStr += values[i] + " = ?";
        }

        try {
            System.out.println(queryStr);
            var query = TeamTable.conn.prepareStatement(queryStr);
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

            var rs = query.executeQuery();
            while (rs.next()) {
                addr.add(new Address(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return addr;
    }


    public Integer insert(Object ... values) throws SQLException {
       try {
            var index = 1;
            var insertStatement = TeamTable.conn.prepareStatement("INSERT INTO ADDRESS VALUES (?, ?, ?, ?)");
            for (Object o : values) {
               if (o instanceof String) {
                   insertStatement.setString(index, (String)o);
                   index++;
                }

               if (o instanceof Integer){
                   insertStatement.setInt(index, (Integer)o);
                   index++;
               }
            }
           insertStatement.executeUpdate();
       } catch (SQLException e) {
           System.out.println(e);
       }

        return 1;
    }

    public Integer update(Integer id, Object ... values) throws SQLException {
        try {
            var index = 1;
            var updateStatement = TeamTable.conn.prepareStatement("UPDATE ADDRESS SET city = ?, country = ?, address_line = ? WHERE address_id = ?");
            for (Object o : values) {
                if (o instanceof String) {
                    updateStatement.setString(index, (String)o);
                    index++;
                }

                if (o instanceof Integer){
                    updateStatement.setInt(index, (Integer)o);
                    index++;
                }
            }
            updateStatement.setInt(index, id);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 1;
    }

    public Integer delete(Object ... values) {
        try {
            var index = 1;
            var deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM ADDRES WHERE ? = ?");
            for (Object o : values) {
                if (o instanceof String) {
                    deleteStatement.setString(index, (String)o);
                    index++;
                }

                if (o instanceof Integer){
                    deleteStatement.setInt(index, (Integer)o);
                    index++;
                }
            }
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 1;
    }
}
