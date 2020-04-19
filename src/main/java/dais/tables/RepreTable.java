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

    public int insert(int id, String n) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("INSERT INTO REPRE (repre_id, team_name) VALUES (" + id + ", "+ n +")");
    }

    public int update(int id, String n) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("UPDATE REPRE team_name = " + n + " WHERE repre_id = " + id + "");
    }

    public int delete(int column, int value) throws SQLException {
        return TeamTable.conn.createStatement().executeUpdate("DELETE FROM REPRE WHERE "+ column +"="+ value +"");
    }
}
