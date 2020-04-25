package dais.tables;

import dais.entities.Address;

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
        //return TeamTable.conn.createStatement().executeUpdate("INSERT INTO ADDRESS (address_id, city, country, address_line) VALUES (" + id.toString() + ", '" + c + "', '"+ cou +"', '" + a +"')");
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
