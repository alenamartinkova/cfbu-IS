package vis.business;

public class League {
    Integer leagueID;
    String name;
    Integer category;

    public League(Integer id, String n,  Integer c) {
        this.leagueID = id;
        this.name = n;
        this.category = c;
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

