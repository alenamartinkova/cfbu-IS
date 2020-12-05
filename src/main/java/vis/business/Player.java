package vis.business;

import vis.gateways.PlayerGateway;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Player {
    Integer id;
    Integer teamID;
    String name;
    String sureName;
    Timestamp dateOfBirth;
    Integer covid;
    Timestamp quarantinedFrom;
    String email;
    String stick;

    public Player(){};
    public Player(Integer id, Integer tID, String n, String sn, String d, Integer c, String q, String e, String s) {
        this.id = id;
        this.name = n;
        this.sureName = sn;
        this.teamID = tID;
        this.dateOfBirth = Timestamp.valueOf(d);
        this.covid = c;
        try {
            this.quarantinedFrom = Timestamp.valueOf(q);
        } catch (IllegalArgumentException ex) {
            this.quarantinedFrom = null;
        }
        this.email = e;
        this.stick = s;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getCovid() {
        return this.covid;
    }

    public Timestamp getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Timestamp getQuarantinedFrom() {
        return this.quarantinedFrom;
    }

    public Integer getTeamID() {
        return this.teamID;
    }

    public String getSureName() {
        return this.sureName;
    }

    public String getStick() {
        return this.stick;
    }

    public String getEmail() {
        return this.email;
    }

    public static void update(Player p) throws SQLException {
        PlayerGateway.update(p);
    }

    public static void updateAndResetStats(Player p, Integer sID) throws SQLException {
        PlayerGateway.updateAndResetStats(p, sID);
    }

    public static void insert(Player p) throws SQLException {
        PlayerGateway.insert(p);
    }

    public static Player fetchByID(Integer pID) {
        return PlayerGateway.fetchByID(pID);
    }

    public static ArrayList<Player> fetch() throws SQLException {
        return PlayerGateway.fetch();
    }

    public static ArrayList<Player> searchByAttr(String s) {
        return PlayerGateway.searchByAttr(s);
    }
}
