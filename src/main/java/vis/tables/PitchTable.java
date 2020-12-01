package vis.tables;

import vis.entities.Pitch;
import vis.entities.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class PitchTable extends Table {
    public PitchTable() throws SQLException {
        super("Pitch");

        this.columns = new ArrayList<>(
                Arrays.asList("pitchID", "addressID", "capacity", "name")
        );
    };

    public ArrayList<Pitch> fetch() throws SQLException {
        ResultSet rs = this.conn.createStatement().executeQuery("SELECT * FROM Pitch");
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
            PreparedStatement query = this.conn.prepareStatement(queryStr);
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


    public Integer insert(Pitch pitch) {
        String query = this.buildInsert(3, 1);

        PreparedStatement preparedQuery = null;
        int output = 0;
        try {
            preparedQuery = this.conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedQuery.setInt(1, pitch.getAddressID());
            preparedQuery.setInt(2, pitch.getCapacity());
            preparedQuery.setString(3, pitch.getName());

            output = preparedQuery.executeUpdate();

            try (ResultSet generatedKeys = preparedQuery.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    output = (int) generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating pitch failed, no ID obtained.");
                }
            }

            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public int update(Pitch pitch) {
        int output = 0;

        String query = this.buildUpdate(1);

        PreparedStatement preparedQuery = null;
        try {
            preparedQuery = this.conn.prepareStatement(query);
            preparedQuery.setInt(1, pitch.getAddressID());
            preparedQuery.setInt(2, pitch.getCapacity());
            preparedQuery.setString(3, pitch.getName());
            preparedQuery.setInt(4, pitch.getPitchID());

            output = preparedQuery.executeUpdate();
            preparedQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    public Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = this.conn.prepareStatement("DELETE FROM Pitch WHERE pitchID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }
}
