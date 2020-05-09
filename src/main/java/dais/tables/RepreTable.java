package dais.tables;

import dais.entities.Repre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepreTable {
    public RepreTable(){};

    public List<Repre> fetch() throws SQLException {
        ResultSet rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM REPRE");
        ArrayList<Repre> repres = new ArrayList<Repre>();
        while (rs.next()) {
            repres.add(new Repre(rs.getInt(1), rs.getString(2)));
        }

        rs.close();
        return repres;
    }

    public List<Repre> fetchByAttr(Object ... values) {
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");
        ArrayList<Repre> repre = new ArrayList<Repre>();

        String queryStr = "SELECT * FROM REPRE WHERE ";
        for (int i = 0; i < values.length; i++) {
            if (i%2 == 0 && i != values.length - 2) queryStr += values[i] + " = ? AND ";
            else if (i%2 == 0 && i == values.length - 2) queryStr += values[i] + " = ?";
        }

        try {
            PreparedStatement query = TeamTable.conn.prepareStatement(queryStr);
            Integer index = 1;
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
                repre.add(new Repre(rs.getInt(1), rs.getString(2)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return repre;
    }

    public Integer insert(Object ... values) {
        try {
            Integer index = 1;
            PreparedStatement insertStatement = TeamTable.conn.prepareStatement("INSERT INTO REPRE VALUES (?, ?)");
            for (Object o : values) {
                if (o instanceof String) {
                    insertStatement.setString(index, (String)o);
                    index++;
                }

                if (o instanceof Integer ){
                    insertStatement.setInt(index, (Integer)o);
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
            Integer index = 1;
            PreparedStatement updateStatement = TeamTable.conn.prepareStatement("UPDATE REPRE SET team_name = ? WHERE repre_id = ?");
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
            PreparedStatement deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM REPRE WHERE REPRE_ID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }
}
