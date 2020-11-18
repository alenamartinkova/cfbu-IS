package vis.tables;

import vis.entities.Referee;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RefereeTable {
    public RefereeTable(){};

    public ArrayList<Referee> fetch() throws SQLException {
        ResultSet rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM Referee");
        ArrayList<Referee> referees = new ArrayList<>();
        while (rs.next()) {
            referees.add(new Referee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5)));
        }

        rs.close();
        return referees;
    }

    public ArrayList<Referee> fetchByAttr(Object ... values) {
        ArrayList<Referee> referee = new ArrayList<>();
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");

        String queryStr = "SELECT * FROM Referee WHERE ";
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

                    if (values[i] instanceof Date) {
                        query.setDate(index, (Date) values[i]);
                        index++;
                    }
                }
            }

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                referee.add(new Referee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return referee;
    }


    public Integer insert(Object ... values) {
        try {
            Integer index = 1;
            PreparedStatement insertStatement = TeamTable.conn.prepareStatement("INSERT INTO Referee VALUES (?, ?, ?, ?)");
            for (Object o : values) {
                if (o instanceof String) {
                    insertStatement.setString(index, (String)o);
                    index++;
                }

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
            PreparedStatement updateStatement = TeamTable.conn.prepareStatement("UPDATE Referee SET name = ?, sureName = ?, email = ?, dateOfBirth = ?  WHERE refereeID = ?");
            for (Object o : values) {
                if (o instanceof String) {
                    updateStatement.setString(index, (String)o);
                    index++;
                }

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
            PreparedStatement deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM Referee WHERE refereeID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }
}
