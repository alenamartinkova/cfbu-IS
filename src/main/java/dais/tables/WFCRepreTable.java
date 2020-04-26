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

    public Integer insert(Object ... values) {
        try {
            var index = 1;
            var insertStatement = TeamTable.conn.prepareStatement("INSERT INTO WFC_REPRE VALUES (?, ?, ?)");
            for (Object o : values) {
                if (o instanceof String) {
                    insertStatement.setString(index, (String)o);
                    index++;
                }

                if (o instanceof Integer ){
                    insertStatement.setInt(index, (Integer) o);
                    index++;
                }
            }
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 1;
    }

    public Integer update(Integer r_id, Integer w_id, Integer rank) {
        try {
            var insertStatement = TeamTable.conn.prepareStatement("UPDATE WFC_REPRE SET rank = ? WHERE repre_id = ? AND wfc_id = ?");
            insertStatement.setInt(1, rank);
            insertStatement.setInt(2, r_id);
            insertStatement.setInt(3, w_id);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 1;
    }

    public Integer delete(Object ... values) {
        try {
            var index = 1;
            var deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM WFC_REPRE WHERE ? = ?");
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
