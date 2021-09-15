package org.alenamartinkova.dais.gateways;

import org.alenamartinkova.dais.DTO.PitchDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class PitchGateway {
    static final ArrayList<String> columns = new ArrayList<>(
            Arrays.asList("pitchID", "addressID", "capacity", "name")
    );
    public PitchGateway() {}

    public static ArrayList<PitchDTO> fetch() throws SQLException {
        ResultSet rs = Table.conn.createStatement().executeQuery("SELECT * FROM Pitch");
        ArrayList<PitchDTO> pitches = new ArrayList<>();
        while (rs.next()) {
            pitches.add(new PitchDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4)));
        }

        rs.close();
        return pitches;
    }

    public static Integer insert(PitchDTO pitch) throws SQLException {
        Table t = new Table("Pitch", columns);
        String query = t.buildInsert(3, 1);

        PreparedStatement preparedQuery = null;
        int output = 0;
        try {
            preparedQuery = Table.conn.prepareStatement(query,
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

    public static Integer update(PitchDTO pitch) throws SQLException {
        int output = 0;

        Table t = new Table("Pitch", columns);
        String query = t.buildUpdate(1);

        PreparedStatement preparedQuery = null;
        try {
            preparedQuery = Table.conn.prepareStatement(query);
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

    public static Integer delete(Integer id) {
        try {
            PreparedStatement deleteStatement = Table.conn.prepareStatement("DELETE FROM Pitch WHERE pitchID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static PitchDTO fetchByID(Integer id) {
        String queryStr = "SELECT * FROM Pitch WHERE pitchID = ?";
        String val = id.toString();
        PitchDTO pitch = new PitchDTO();
        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);
            query.setString(1, val);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                pitch = new PitchDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pitch;
    }

    public static PitchDTO fetchByName(String name) {
        String queryStr = "SELECT * FROM Pitch WHERE name = ?";
        PitchDTO pitch = new PitchDTO();
        try {
            PreparedStatement query = Table.conn.prepareStatement(queryStr);
            query.setString(1, name);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                pitch = new PitchDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pitch;
    }
}
