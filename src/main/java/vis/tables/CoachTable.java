package vis.tables;

import vis.entities.Coach;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class CoachTable extends Table{
    public CoachTable() throws SQLException {
        super("Coach");

        this.columns = new ArrayList<>(
                Arrays.asList("memberID", "teamID", "name", "sureName", "dateOfBirth", "covid", "quarantinedFrom", "email", "license")
        );
    };

    public ArrayList<Coach> fetch() throws SQLException {
        ResultSet rs = this.conn.createStatement().executeQuery("SELECT * FROM COACH");
        ArrayList<Coach> coaches = new ArrayList<>();
        while (rs.next()) {
            coaches.add(new Coach(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getInt(6), rs.getDate(7), rs.getString(8), rs.getString(9)));
        }

        rs.close();
        return coaches;
    }

    public ArrayList<Coach> fetchByAttr(Object ... values) {
        ArrayList<Coach> coach = new ArrayList<>();
        if (values.length % 2 != 0 || values.length == 0) throw new IllegalArgumentException("There must be even number of arguments.");

        String queryStr = "SELECT * FROM Coach WHERE ";
        for (int i = 0; i < values.length; i++) {
            if (i%2 == 0 && i != values.length - 2) queryStr += values[i] + " = ? AND ";
            else if (i%2 == 0 && i == values.length - 2) queryStr += values[i] + " = ?";
        }

        try {
            PreparedStatement query = this.conn.prepareStatement(queryStr);
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
                coach.add(new Coach(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getInt(6), rs.getDate(7), rs.getString(8), rs.getString(9)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return coach;
    }

    public Integer insert(Object ... values) {
        try {
            Integer index = 1;
            PreparedStatement insertStatement = this.conn.prepareStatement("INSERT INTO Coach VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
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
            PreparedStatement updateStatement = this.conn.prepareStatement("UPDATE Coach SET teamID = ?, name = ?, sureName = ?, dateOfBirth = ?, covid = ?, quarantinedFrom = ?, email = ?, license = ? WHERE coachID = ?");
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
            PreparedStatement deleteStatement = this.conn.prepareStatement("DELETE FROM Coach WHERE coachID = "+ id.toString() +"");
            return deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }
}
