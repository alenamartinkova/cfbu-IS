package dais.tables;
import dais.entities.WFC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WFCTable {
    private WFCTable(){};

    public List<WFC> fetch() throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM WFC");
        var wfcs = new ArrayList<WFC>();
        while (rs.next()) {
            wfcs.add(new WFC(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
        }

        rs.close();
        return wfcs;
    }

    public int insert(int id, int y, int a_id) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("INSERT INTO WFC (wfc_id, year, address_id) VALUES (" + id + "," + y +", "+ a_id +")");
    }

    public int update(int id, int y, int a_id) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("UPDATE WFC SET year = "+ y + ", address_id = " + a_id +" WHERE wfc_id = "+ id + "");
    }

    public int delete(int column, int value) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("DELETE FROM WFC WHERE "+ column +"="+ value +"");
    }
}
