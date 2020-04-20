package dais.entities;

public class Team {
    Integer id;
    Integer rank;
    String name;
    Integer league_id;

    public Team(Integer id, Integer r, String n, Integer l) {
        this.id = id;
        this.rank = r;
        this.name = n;
        this.league_id = l;
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

    public Integer getLeagueId() {
        return this.league_id;
    }

}
