package dais;

import dais.tables.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        AddressTable at = new AddressTable();
        LeagueTable lt = new LeagueTable();
        PlayerTable pt = new PlayerTable();
        RepreTable rt = new RepreTable();
        TeamTable tt = new TeamTable();
        WFCTable wt = new WFCTable();

       runTests(at, lt, pt, rt, tt, wt);
    }

    private static void runTests(AddressTable at, LeagueTable lt, PlayerTable pt, RepreTable rt, TeamTable tt, WFCTable wt) throws SQLException {
        // ADDRESS TABLE TESTS
        //SELECT
        System.out.println(at.fetch());
        //SELECT BY ATTRIBUTE
        System.out.println(at.fetchByAttr("ADDRESS_ID", 1));
        System.out.println(at.fetchByAttr("CITY", "Ostrava", "COUNTRY", "Czech Republic"));

        //INSERT
        at.insert(42, "Banska Bystrica", "Slovakia", "Moskovska 23");

        //UPDATE
        at.update(42, "Kosice", "Slovakia", "Kosicka 1");

        //DELETE
        at.delete(42);
    }
}