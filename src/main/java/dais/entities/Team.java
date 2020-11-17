package dais.entities;

import java.util.Date;

public class Team {
    Integer id;
    Integer rank;
    String name;
    Integer leagueID;
    Integer covid;
    Date qurantinedFrom;

    public Team(Integer id, Integer l,  String n, Integer r, Integer c, Date q) {
        this.id = id;
        this.rank = r;
        this.name = n;
        this.leagueID = l;
        this.covid = c;
        this.qurantinedFrom = q;
    }

    public Team(){};

    public Integer getId() {
        return this.id;
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

    public Date getQurantinedFrom() {
        return this.qurantinedFrom;
    }
}
