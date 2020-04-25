package dais;

import dais.tables.AddressTable;
import dais.tables.LeagueTable;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        AddressTable at = new AddressTable();
        LeagueTable l = new LeagueTable();
        System.out.println(l.fetch());
        //System.out.println(at.fetchByAttr("ADDRESS_ID", 1));
        //at.insert(36, "BB", "Slovakia", "Moskovska 23");
       // at.delete("ADDRESS_ID", "35");
    }
}