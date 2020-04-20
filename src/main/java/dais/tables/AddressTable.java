package dais.tables;

import dais.entities.Address;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressTable {
    private AddressTable(){};

    public List<Address> fetch() throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM ADDRESS");
        var addresses = new ArrayList<Address>();
        while (rs.next()) {
            addresses.add(new Address(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
        }

        rs.close();
        return addresses;
    }

    public int insert(int id, String c, String cou, String a) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("INSERT INTO ADDRESS (address_id, city, country, address_line) VALUES (" + id + ", " + c + ", "+ cou +", " + a +")");
    }

    public int update(int id, String c, String cou, String a) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("UPDATE ADDRESS SET city = "+ c +", country = " + cou +", address_line = " + a +" WHERE address_id = "+ id +"");
    }

    public int delete(String column, String value) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("DELETE FROM ADDRESS WHERE "+ column +"="+ value +"");
    }
}
