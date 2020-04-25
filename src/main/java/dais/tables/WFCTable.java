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

    public Integer insert(Object ... values) throws SQLException {
        try {
            var index = 1;
            var insertStatement = TeamTable.conn.prepareStatement("INSERT INTO WFC VALUES (?, ?, ?)");
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

    public Integer update(Integer id, Object ... values) throws SQLException {
        try {
            var index = 1;
            var updateStatement = TeamTable.conn.prepareStatement("UPDATE WFC SET year = ?, address_id = ? WHERE wfc_id = ?");
            for (Object o : values) {
                if (o instanceof String) {
                    updateStatement.setString(index, (String)o);
                    index++;
                }

                if (o instanceof Integer ){
                    updateStatement.setInt(index, (Integer) o);
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
            var deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM WFC WHERE ? = ?");
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

    public WFC wfcDetail(Integer id) throws SQLException {
        var query = TeamTable.conn.prepareStatement("SELECT * FROM WFC WHERE wfc_id = ?");
        query.setInt(1, id);
        var rs = query.executeQuery();
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
