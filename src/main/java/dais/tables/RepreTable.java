package dais.tables;

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
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return 1;
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
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return 1;
    }

    public Integer delete(Object ... values) {
        try {
            var index = 1;
            var deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM REPRE WHERE ? = ?");
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
