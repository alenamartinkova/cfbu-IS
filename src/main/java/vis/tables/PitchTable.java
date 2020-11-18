package vis.tables;

import vis.entities.Pitch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PitchTable {
    public PitchTable(){};

    public ArrayList<Pitch> fetch() throws SQLException {
        ResultSet rs = TeamTable.conn.createStatement().executeQuery("SELECT * FROM Pitch");
        ArrayList<Pitch> pitches = new ArrayList<>();
        while (rs.next()) {
            pitches.add(new Pitch(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4)));
        }

        rs.close();
        return pitches;
    }

    public ArrayList<Pitch> fetchByAttr(Object ... values) {
        ArrayList<Pitch> pitch = new ArrayList<>();
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");

        String queryStr = "SELECT * FROM Pitch WHERE ";
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

                    if (values[i] instanceof String) {
                        query.setString(index, (String) values[i]);
                        index++;
                    }
                }
            }

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                pitch.add(new Pitch(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return pitch;
    }


    public Integer insert(Object ... values) {
        try {
            Integer index = 1;
            PreparedStatement insertStatement = TeamTable.conn.prepareStatement("INSERT INTO PITCH VALUES (?, ?, ?)");
            for (Object o : values) {
                if (o instanceof Integer){
                    insertStatement.setInt(index, (Integer)o);
                    index++;
                }

                if (o instanceof String){
                    insertStatement.setString(index, (String) o);
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
            PreparedStatement updateStatement = TeamTable.conn.prepareStatement("UPDATE Pitch SET addressID = ?, capacity = ?, name = ? WHERE pitchID = ?");
            for (Object o : values) {
                if (o instanceof Integer){
                    updateStatement.setInt(index, (Integer) o);
                    index++;
                }

                if (o instanceof String){
                    updateStatement.setString(index, (String) o);
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
            PreparedStatement deleteStatement = TeamTable.conn.prepareStatement("DELETE FROM Pitch WHERE pitchID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }
}
