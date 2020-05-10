package dais.tables;

import dais.entities.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamTable {
    public TeamTable(){};
    public static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@dbsys.cs.vsb.cz:1521:oracle","mar0702", "R5ddxZ4NnO");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Team> fetch() throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TEAM");
        ArrayList<Team> teams = new ArrayList<Team>();
        while (rs.next()) {
             teams.add(new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)));
        }

         rs.close();
         return teams;
    }

    public Integer insert(Object ... values) throws SQLException {
        Integer id = getMaxId() + 1;

        if (id == -1) {
            System.out.println("Error");
            return -2;
        }

        try {
            Integer index = 1;
            PreparedStatement insertStatement = TeamTable.conn.prepareStatement("INSERT INTO TEAM VALUES ("+id+", ?, ?, ?)");
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
            PreparedStatement updateStatement = TeamTable.conn.prepareStatement("UPDATE TEAM SET rank = ?, name = ?, league_id = ? WHERE team_id = ?");
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

    public ArrayList<Team> fetchByAttr(Object ... values) {
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");
        ArrayList<Team> team = new ArrayList<Team>();

        String queryStr = "SELECT * FROM TEAM WHERE ";
        for (int i = 0; i < values.length; i++) {
            if (i%2 == 0 && i != values.length - 2) queryStr += values[i] + " = ? AND ";
            else if (i%2 == 0 && i == values.length - 2) queryStr += values[i] + " = ?";
        }

        try {
            PreparedStatement query = conn.prepareStatement(queryStr);
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
                team.add(new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return team;
    }

    public ArrayList<Team> searchByAttr(String val) {
        ArrayList<Team> team = new ArrayList<Team>();

        String queryStr = "SELECT * FROM TEAM WHERE TEAM_ID LIKE ? OR RANK LIKE ? OR NAME LIKE ? OR LEAGUE_ID LIKE ?";

        try {
            PreparedStatement query = conn.prepareStatement(queryStr);

            for (int i = 1; i <= 4; i++) {
                query.setString(i, "%" + val + "%");
            }

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                team.add(new Team(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return team;
    }


    public Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM TEAM WHERE TEAM_ID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }

    public Integer getMaxId() throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT MAX(TEAM_ID) FROM TEAM");
        while (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public Integer getMaxRank(Integer league_id) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT MAX(RANK) FROM TEAM WHERE LEAGUE_ID = "+league_id+"");
        while (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public void teamTransfer(Integer team_id, Integer league_id) {
        try (
                CallableStatement statement = TeamTable.conn.prepareCall(" {call transferTeam(?, ?)}");
        ) {
            statement.setInt(1, team_id );
            statement.setInt(2, league_id );
            statement.execute();
            statement.close();
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeLeague(String name, Integer division, Integer old_league_id) {
        try (
                CallableStatement statement = TeamTable.conn.prepareCall(" {call changeLeague(?, ?, ?)}");
        ) {
            statement.setString(1, name );
            statement.setInt(2, division );
            statement.setInt(3, old_league_id );
            statement.execute();
            statement.close();
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
