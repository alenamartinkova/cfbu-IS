package dais;

import dais.tables.AddressTable;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        AddressTable at = new AddressTable();
        at.insert(35, "BB", "Slovakia", "Moskovska 23");
       // at.delete("ADDRESS_ID", "35");
    }
}