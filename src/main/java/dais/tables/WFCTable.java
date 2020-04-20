package dais.tables;
import dais.entities.WFC;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WFCTable {
    public WFCTable(){};

    public List<WFC> fetch() throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM WFC");
        var wfcs = new ArrayList<WFC>();
        while (rs.next()) {
            wfcs.add(new WFC(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
        }

        rs.close();
        return wfcs;
    }

    public Integer insert(Integer id, Integer y, Integer a_id) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("INSERT INTO WFC (wfc_id, year, address_id) VALUES (" + id.toString() + "," + y.toString() +", "+ a_id.toString() +")");
    }

    public Integer update(Integer id, Integer y, Integer a_id) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("UPDATE WFC SET year = "+ y.toString() + ", address_id = " + a_id.toString() +" WHERE wfc_id = "+ id.toString() + "");
    }

    public Integer delete(String column, String value) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("DELETE FROM WFC WHERE '"+ column +"'='"+ value +"'");
    }

    public WFC wfcDetail(Integer id) throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM WFC WHERE wfc_id = " + id.toString() +"");
        var wfc = new WFC();
        while (rs.next()) {
            wfc = new WFC(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }

        rs.close();
        return wfc;
    }

    public void changeWFCAddress(Integer id, Integer address_id) {
        try (
                CallableStatement statement = TeamTable.conn.prepareCall(" {call changeWFCAddress(?, ?)}");
        ) {
            statement.setInt(1, id );
            statement.setInt(2, address_id );
            statement.execute();
            statement.close();
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
