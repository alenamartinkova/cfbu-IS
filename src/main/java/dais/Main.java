package dais;

import dais.tables.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        /*TeamTable tt = new TeamTable();
        tt.teamTransfer(28, 7); */
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

        //PLAYER TRANSFER PROCEDURE
        pt.playerTransfer(1, 1);

        //REPRE TEAMMATERS FUNCTION
        //FOR THIS YEAR IT RETURNS []
        pt.repreTeammates(1);

         /*
        REPRE TABLE TESTS
         */
        //SELECT
        System.out.println(rt.fetch());
        //SELECT BY ATTRIBUTE
        System.out.println(rt.fetchByAttr("REPRE_ID", 8));

        //INSERT
        System.out.println(rt.insert(17, "Uganda"));

        //UPDATE
        System.out.println(rt.update(17, "Keňa"));

        //DELETE
        System.out.println(rt.delete(17));

         /*
        TEAM TABLE TESTS
         */
        //SELECT
        System.out.println(tt.fetch());
        //SELECT BY ATTRIBUTE
        System.out.println(tt.fetchByAttr("TEAM_ID", 8));

        //INSERT
        System.out.println(tt.insert( 9, "Team test", 3));

        //UPDATE
        System.out.println(tt.update(29, 9, "Team update test", 3));

        //DELETE
        System.out.println(tt.delete(29));

        //TEAM TRANSFER
        tt.teamTransfer(1,2);

        //CREATE NEW LEAGUE AND ADD ALL THE TEAMS FROM OLD LEAGUE TO IT
        tt.changeLeague("TEST", 1, 1);


        /*
        WFC TABLE TESTS
         */
        //SELECT
        System.out.println(wt.fetch());
        //SELECT BY ATTRIBUTE
        System.out.println(wt.fetchByAttr("WFC_ID", 2));

        //INSERT
        System.out.println(wt.insert(5, 2021, 4));

        //UPDATE
        System.out.println(wt.update(5, 2021, 5));

        // DELETE
        System.out.println(wt.delete(5));

        // CHANGE WFC ADDRESS
        wt.changeWFCAddress(1,29);
    }
}