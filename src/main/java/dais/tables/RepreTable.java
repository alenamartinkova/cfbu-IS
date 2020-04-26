package dais.tables;

import dais.entities.League;
import dais.entities.Repre;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepreTable {
    public RepreTable(){};

    public List<Repre> fetch() throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM REPRE");
        var repres = new ArrayList<Repre>();
        while (rs.next()) {
            repres.add(new Repre(rs.getInt(1), rs.getString(2)));
        }

        rs.close();
        return repres;
    }

    public List<Repre> fetchByAttr(Object ... values) {
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");
        var repre = new ArrayList<Repre>();

        var queryStr = "SELECT * FROM REPRE WHERE ";
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

            var rs = query.executeQuery();
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
            var index = 1;
            var insertStatement = TeamTable.conn.prepareStatement("INSERT INTO REPRE VALUES (?, ?)");
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
            var index = 1;
            var updateStatement = TeamTable.conn.prepareStatement("UPDATE REPRE SET team_name = ? WHERE repre_id = ?");
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
            var deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM REPRE WHERE REPRE_ID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }

    public Repre repreDetail(Integer id) throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM REPRE WHERE repre_id = "+ id.toString() + "");
        var repre = new Repre();
        while (rs.next()) {
            repre = new Repre(rs.getInt(1), rs.getString(2));
        }

        rs.close();
        return repre;
    }
}
