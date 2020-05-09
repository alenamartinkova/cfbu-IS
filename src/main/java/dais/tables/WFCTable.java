package dais.tables;
import dais.entities.WFC;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WFCTable {
    public WFCTable(){};

    public List<WFC> fetch() throws SQLException {
        ResultSet rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM WFC");
        var wfcs = new ArrayList<WFC>();
        while (rs.next()) {
            wfcs.add(new WFC(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
        }

        rs.close();
        return wfcs;
    }

    public List<WFC> fetchByAttr(Object ... values) {
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");
        var wfc = new ArrayList<WFC>();

        var queryStr = "SELECT * FROM WFC WHERE ";
        for (int i = 0; i < values.length; i++) {
            if (i%2 == 0 && i != values.length - 2) queryStr += values[i] + " = ? AND ";
            else if (i%2 == 0 && i == values.length - 2) queryStr += values[i] + " = ?";
        }

        try {
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

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                wfc.add(new WFC(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return wfc;
    }


    public Integer insert(Object ... values) {
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

            return insertStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }

    public Integer update(Integer id, Object ... values) {
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
            return updateStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }

    public Integer delete(Integer id) {
        try {
            var deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM WFC WHERE WFC_ID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
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
