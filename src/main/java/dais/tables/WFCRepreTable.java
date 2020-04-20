package dais.tables;

import dais.entities.WFCRepre;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WFCRepreTable {
    private WFCRepreTable(){};
    public List<WFCRepre> fetch() throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM WFC_REPRE");
        var wfc_repres = new ArrayList<WFCRepre>();
        while (rs.next()) {
            wfc_repres.add(new WFCRepre(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
        }

        rs.close();
        return wfc_repres;
    }

    public int insert(int id, int r, int r_id) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("INSERT INTO WFC_REPRE (wfc_id, rank, repre_id) VALUES (" + id + "," + r +", "+ r_id +")");
    }

    public int update(int id, int r, int r_id) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("UPDATE WFC_REPRE SET rank = " + r + " WHERE repre_id = " + r_id +" AND wfc_id = " + id + "");
    }

    public int delete(String column, String value) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("DELETE FROM WFC_REPRE WHERE "+ column +"="+ value +"");
    }
}
