package business;

import gateways.TeamGateway;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Team {
    Integer teamid;
    Integer rank;
    String name;
    Integer leagueID;
    Integer covid;
    Timestamp quarantinedFrom;

    public Team(Integer id, Integer l,  String n, Integer r, Integer c, String q) {
        this.teamid = id;
        this.rank = r;
        this.name = n;
        this.leagueID = l;
        this.covid = c;
        try {
            this.quarantinedFrom = Timestamp.valueOf(q);
        } catch (IllegalArgumentException ex) {
            this.quarantinedFrom = null;
        }
    }

    public Team(){};

    public Integer getId() {
        return this.teamid;
    }

    public Integer getRank() {
        return this.rank;
    }

    public String getName() {
        return this.name;
    }

    public Integer getLeagueID() {
        return this.leagueID;
    }

    public Integer getCovid() {
        return this.covid;
    }

    public Timestamp getQurantinedFrom() {
        return this.quarantinedFrom;
    }

    public static Team fetchByID(Integer tID) {
        return TeamGateway.fetchByID(tID);
    }

    public static ArrayList<Team> fetch() throws SQLException {
        return TeamGateway.fetch();
    }
}
