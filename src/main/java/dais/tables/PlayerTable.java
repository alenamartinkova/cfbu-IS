package dais.tables;

import dais.entities.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerTable {
    public PlayerTable(){};

    public ArrayList<Player> fetch() throws SQLException {
        ResultSet rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM PLAYER");
        ArrayList<Player> players = new ArrayList<>();
        while (rs.next()) {
            players.add(new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
        }

        rs.close();
        return players;
    }

    public ArrayList<Player> fetchByAttr(Object ... values) {
        ArrayList<Player> player = new ArrayList<>();
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");

        String queryStr = "SELECT * FROM PLAYER WHERE ";
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
                player.add(new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return player;
    }


    public Integer insert(Object ... values) {
        try {
            Integer index = 1;
            PreparedStatement insertStatement = TeamTable.conn.prepareStatement("INSERT INTO PLAYER VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
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
            PreparedStatement updateStatement = TeamTable.conn.prepareStatement("UPDATE PLAYER SET first_name = ?, last_name = ?, assists = ?, goals = ?, address_id = ?, team_id = ?, year_born = ? WHERE player_id = ?");
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
            PreparedStatement deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM PLAYER WHERE PLAYER_ID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }

    public void playerTransfer(Integer player_id, Integer team_id) {
        try (
                CallableStatement statement = TeamTable.conn.prepareCall(" {call playerTransfer(?, ?)}");
        ) {
            statement.setInt(1, player_id );
            statement.setInt(2, team_id );
            statement.execute();
            statement.close();
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Player> repreTeammates(Integer p_id) {
        ArrayList<Player> players = new ArrayList<>();

        try (
                CallableStatement statement = TeamTable.conn.prepareCall("{? = call repreTeammatesFunc(?)}");
        ) {
            statement.registerOutParameter(1, Types.REF_CURSOR);
            statement.setInt(2, p_id );
            statement.executeQuery();
            ResultSet rs = statement.getObject(1, ResultSet.class);

            while (rs.next()) {
                players.add(new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
            }
            statement.close();
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return players;
    }
}
