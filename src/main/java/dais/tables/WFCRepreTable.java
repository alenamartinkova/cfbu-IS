package dais.tables;

import dais.entities.WFCRepre;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WFCRepreTable {
    public WFCRepreTable(){};
    public List<WFCRepre> fetch() throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM WFC_REPRE");
        var wfc_repres = new ArrayList<WFCRepre>();
        while (rs.next()) {
            wfc_repres.add(new WFCRepre(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
        }

        rs.close();
        return wfc_repres;
    }

    public Integer insert(Integer id, Integer r, Integer r_id) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("INSERT INTO WFC_REPRE (wfc_id, rank, repre_id) VALUES (" + id.toString() + "," + r.toString() +", "+ r_id.toString() +")");
    }

    public Integer update(Integer id, Integer r, Integer r_id) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("UPDATE WFC_REPRE SET rank = " + r.toString() + " WHERE repre_id = " + r_id.toString() +" AND wfc_id = " + id.toString() + "");
    }

    public Integer delete(String column, String value) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("DELETE FROM WFC_REPRE WHERE "+ column +"="+ value +"");
    }
}
