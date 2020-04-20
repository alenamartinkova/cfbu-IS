package dais.tables;

import dais.entities.Repre;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepreTable {
    private RepreTable(){};

    public List<Repre> fetch() throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM REPRE");
        var repres = new ArrayList<Repre>();
        while (rs.next()) {
            repres.add(new Repre(rs.getInt(1), rs.getString(2)));
        }

        rs.close();
        return repres;
    }

    public Integer insert(Integer id, String n) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("INSERT INTO REPRE (repre_id, team_name) VALUES (" + id.toString() + ", "+ n +")");
    }

    public Integer update(Integer id, String n) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("UPDATE REPRE team_name = " + n + " WHERE repre_id = " + id.toString() + "");
    }

    public Integer delete(String column, String value) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("DELETE FROM REPRE WHERE "+ column +"="+ value +"");
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
