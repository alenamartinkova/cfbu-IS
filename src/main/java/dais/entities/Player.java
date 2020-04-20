package dais.entities;

public class Player {
    Integer id;
    String first_name;
    String last_name;
    Integer assists;
    Integer goals;
    Integer a_id;
    Integer t_id;
    Integer year_born;

    public Player(Integer id, String f, String l, Integer a, Integer g, Integer a_id, Integer t_id, Integer y) {
        this.id = id;
        this.first_name = f;
        this.last_name = l;
        this.assists = a;
        this.goals = g;
        this.a_id = a_id;
        this.t_id = t_id;
        this.year_born = y;
    }

    public Player(){};

    public Integer getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public Integer getAssists() {
        return this.assists;
    }

    public Integer getGoals() {
        return this.goals;
    }

    public Integer getAId() {
        return this.a_id;
    }

    public Integer getTId() {
        return this.t_id;
    }

    public Integer getYearBorn() {
        return this.year_born;
    }

}
