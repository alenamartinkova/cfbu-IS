package org.alenamartinkova.dais.gateways;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Table {
    public static String tableName;
    public static Connection conn = null;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://dbsys.cs.vsb.cz\\STUDENT","mar0702", "XPNAL0PmSe");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ArrayList<String> columns;

    public Table(String tableName, ArrayList columns) throws SQLException {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String buildInsert(int valuesCount, int from_column) {
        StringBuilder query = new StringBuilder("INSERT INTO " + this.tableName + "(");

        query.append(String.join(", ", this.columns.subList(from_column, this.columns.size())));
        query.append(") VALUES (");
        for(int i = 0; i < valuesCount; i++) {
            if(i == valuesCount - 1) {
                query.append("?");
            } else {
                query.append("?, ");
            }
        }
        query.append(")");

        var q = query.toString();
        System.out.println(q);

        return q;
    }

    public String buildUpdate(int from_column) {
        StringBuilder query = new StringBuilder("UPDATE " + this.tableName + " SET ");

        for(int i = from_column; i < this.columns.size(); i++) {
            query.append(this.columns.get(i)).append(" = ");

            if( i == this.columns.size() - 1) {
                query.append("?");
            } else {
                query.append("?, ");
            }
        }

        query.append(" WHERE ").append(this.columns.get(0)).append(" = ?");

        var q = query.toString();
        System.out.println(q);

        return q;
    }
}
