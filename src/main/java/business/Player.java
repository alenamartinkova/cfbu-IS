package business;

import DTO.PlayerDTO;
import gateways.PlayerGateway;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    public PlayerDTO toDTO() {
        return null;
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

    /**
     * Function that calls update on player gateway
     * @param p updated player
     * @throws SQLException
     */
    public static void update(Player p) throws SQLException {
        PlayerGateway.update(p);
    }

    /**
     * Function that calls update and reset stats function on player gateway
     * @param p updated player
     * @throws SQLException
     */
    public static void updateAndResetStats(Player p) throws SQLException {
        Statistics s = Statistics.fetchByPlayerID(p.getId());
        PlayerGateway.updateAndResetStats(p, s.getStatsID());
    }

    public static Player fetchByID(Integer pID) {
        return PlayerGateway.fetchByID(pID);
    }

    /**
     * Function that calls gateway search by attribute function
     * @param s searched value
     * @return
     */
    public static ArrayList<Player> searchByAttr(String s) {
        return PlayerGateway.searchByAttr(s);
    }

    /**
     * Function that stores update abortion into log file
     * @throws IOException
     */
    public static void storeError() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./logs/test.txt"));
        lines.add("Update canceled because team was in another league");

        Files.write(Paths.get("./logs/test.txt"), lines,
                StandardCharsets.UTF_8);
    }
}
