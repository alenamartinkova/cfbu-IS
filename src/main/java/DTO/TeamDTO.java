package DTO;

import business.Team;

import java.sql.Timestamp;

public class TeamDTO {
    Integer teamid;
    Integer rank;
    String name;
    Integer leagueID;
    Integer covid;
    Timestamp quarantinedFrom;

    public TeamDTO(){}
    public TeamDTO(Integer id, Integer l,  String n, Integer r, Integer c, String q) {
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

    public Timestamp getQuarantinedFrom() {
        return this.quarantinedFrom;
    }

    public Team toBO() {
        Team team = new Team(this.teamid, this.leagueID, this.name, this.rank, this.covid, this.quarantinedFrom.toString());
        return team;
    }
}
