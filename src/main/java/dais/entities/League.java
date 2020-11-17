package dais.entities;

public class League {
    Integer leagueID;
    Integer category;
    String name;

    public League(Integer id, Integer c, String n) {
        this.leagueID = id;
        this.category = c;
        this.name = n;
    }

    public League(){};

    public Integer getId() {
        return this.leagueID;
    }

    public Integer getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }
}

