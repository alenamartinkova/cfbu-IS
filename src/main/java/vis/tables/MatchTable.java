package vis.tables;
import vis.entities.Match;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MatchTable {
    public MatchTable(){};

    public ArrayList<Match> fetch() throws SQLException {
        ResultSet rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM Match");
        ArrayList<Match> matches = new ArrayList<>();
        while (rs.next()) {
            matches.add(new Match(rs.getInt(1), rs.getInt(2), rs.getDate(3)));
        }

        rs.close();
        return matches;
    }

    public ArrayList<Match> fetchByAttr(Object ... values) {
        ArrayList<Match> match = new ArrayList<>();
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");

        String queryStr = "SELECT * FROM Match WHERE ";
        for (int i = 0; i < values.length; i++) {
            if (i%2 == 0 && i != values.length - 2) queryStr += values[i] + " = ? AND ";
            else if (i%2 == 0 && i == values.length - 2) queryStr += values[i] + " = ?";
        }

        try {
            PreparedStatement query = TeamTable.conn.prepareStatement(queryStr);
            Integer index = 1;
            for (int i = 0; i < values.length; i++) {
                if (i % 2 != 0) {
                    if (values[i] instanceof Integer) {
                        query.setInt(index, (Integer) values[i]);
                        index++;
                    }

                    if (values[i] instanceof Date) {
                        query.setDate(index, (Date) values[i]);
                        index++;
                    }
                }
            }

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                match.add(new Match(rs.getInt(1), rs.getInt(2), rs.getDate(3)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return match;
    }


    public Integer insert(Object ... values) {
        try {
            Integer index = 1;
            PreparedStatement insertStatement = TeamTable.conn.prepareStatement("INSERT INTO MATCH VALUES (?, ?)");
            for (Object o : values) {
                if (o instanceof Integer){
                    insertStatement.setInt(index, (Integer)o);
                    index++;
                }

                if (o instanceof Date){
                    insertStatement.setDate(index, (Date)o);
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
            PreparedStatement updateStatement = TeamTable.conn.prepareStatement("UPDATE MATCH SET postponed = ?, date = ? WHERE matchID = ?");
            for (Object o : values) {
                if (o instanceof Integer){
                    updateStatement.setInt(index, (Integer) o);
                    index++;
                }

                if (o instanceof Date){
                    updateStatement.setDate(index, (Date) o);
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
            PreparedStatement deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM MATCH WHERE matchID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }
}
