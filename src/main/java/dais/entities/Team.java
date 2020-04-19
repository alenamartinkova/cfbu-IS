package dais.entities;

public class Team {
    int id;
    int rank;
    String name;
    int league_id;

    public Team(int id, int r, String n, int l) {
        this.id = id;
        this.rank = r;
        this.name = n;
        this.league_id = l;
    }

    public Team(){};

    public int getId() {
        return this.id;
    }

    public int getRank() {
        return this.rank;
    }

    public String getName() {
        return this.name;
    }

    public int getLeagueId() {
        return this.league_id;
    }

}
