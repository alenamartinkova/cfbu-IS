package dais.entities;

public class Repre {
    Integer id;
    String team_name;

    public Repre(Integer id, String t) {
        this.id = id;
        this.team_name = t;
    }

    public Repre(){};

    public Integer getId() {
        return this.id;
    }

    public String getTeamName() {
        return this.team_name;
    }
}
