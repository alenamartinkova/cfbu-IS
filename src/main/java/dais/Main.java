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
        /*
         ADDRESS TABLE TESTS
        */

        // SELECT
        System.out.println(at.fetch());
        // SELECT BY ATTRIBUTE
        System.out.println(at.fetchByAttr("ADDRESS_ID", 1));
        System.out.println(at.fetchByAttr("CITY", "Ostrava", "COUNTRY", "Czech Republic"));

        // INSERT
        System.out.println(at.insert(42, "Banska Bystrica", "Slovakia", "Moskovska 23"));

        // UPDATE
        System.out.println(at.update(42, "Kosice", "Slovakia", "Kosicka 1"));

        // DELETE
        System.out.println(at.delete(42));

          /*
        LEAGUE TABLE TESTS
         */
        //SELECT
        System.out.println(lt.fetch());
        //SELECT BY ATTRIBUTE
        System.out.println(lt.fetchByAttr("LEAGUE_ID", 1));
        System.out.println(lt.fetchByAttr("DIVISION", 2, "NAME", "Prvá liga"));

        //INSERT
        System.out.println(lt.insert(5, 5, "Random liga"));

        //UPDATE
        System.out.println(lt.update(5, 5, "Update random liga"));

        //DELETE
        System.out.println(lt.delete(5));
        
        /*
        PLAYER TABLE TESTS
         */
        //SELECT
        System.out.println(pt.fetch());
        //SELECT BY ATTRIBUTE
        System.out.println(pt.fetchByAttr("PLAYER_ID", 1));
        System.out.println(pt.fetchByAttr("ASSISTS", 3, "GOALS", 7));

        //INSERT
        System.out.println(pt.insert(61, "Test", "Test", 1, 12, 4, 3, 1997));

        //UPDATE
        System.out.println(pt.update(61, "Test", "Test", 2, 10, 4, 3, 1997));

        //DELETE
        System.out.println(pt.delete(61));
    }
}