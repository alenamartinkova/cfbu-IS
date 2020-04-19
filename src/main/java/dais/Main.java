package dais;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        var conn = DriverManager.getConnection("jdbc:oracle:thin:@dbsys.cs.vsb.cz:1521:oracle","mar0702", "R5ddxZ4NnO");
        var rs = conn.createStatement().executeQuery("SELECT * FROM TEAM");

        while (rs.next()) {
            System.out.println(rs.getString(3));
        }

        rs.close();
    }
}