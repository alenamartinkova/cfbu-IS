package dais.entities;

public class Repre {
    int id;
    String team_name;

    public Repre(int id, String t) {
        this.id = id;
        this.team_name = t;
    }

    public int getId() {
        return this.id;
    }

    public String getTeamName() {
        return this.team_name;
    }
}
