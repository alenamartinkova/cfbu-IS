package dais.tables;

import dais.entities.ReprePlayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReprePlayerTable {
    public ReprePlayerTable(){};

    public List<ReprePlayer> fetch() throws SQLException {
        var rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM REPRE_PLAYER");
        var players = new ArrayList<ReprePlayer>();
        while (rs.next()) {
            players.add(new ReprePlayer(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
        }

        rs.close();
        return players;
    }

    public Integer insert(Object ... values) throws SQLException {
        try {
            var index = 1;
            var insertStatement = TeamTable.conn.prepareStatement("INSERT INTO REPRE_PLAYER VALUES (?, ?, ?)");
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

    public Integer update(Integer id, Object ... values) throws SQLException {
        try {
            var index = 1;
            var updateStatement = TeamTable.conn.prepareStatement("UPDATE REPRE_PLAYER SET year = ? WHERE repre_id = ? AND player_id = ?");
            for (Object o : values) {
                if (o instanceof String) {
                    updateStatement.setString(index, (String)o);
                    index++;
                }

                if (o instanceof Integer ){
                    updateStatement.setInt(index, (Integer)o);
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

    public Integer delete(Object ... values) throws SQLException {
        try {
            var index = 1;
            var deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM REPRE_PLAYER WHERE ? = ?");
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
