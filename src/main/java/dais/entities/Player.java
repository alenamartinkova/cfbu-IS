package dais.entities;

public class Player {
    int id;
    String first_name;
    String last_name;
    java.lang.Integer assists;
    java.lang.Integer goals;
    int a_id;
    int t_id;
    int year_born;

    public Player(int id, String f, String l, java.lang.Integer a, java.lang.Integer g, int a_id, int t_id, int y) {
        this.id = id;
        this.first_name = f;
        this.last_name = l;
        this.assists = a;
        this.goals = g;
        this.a_id = a_id;
        this.t_id = t_id;
        this.year_born = y;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public java.lang.Integer getAssists() {
        return this.assists;
    }

    public java.lang.Integer getGoals() {
        return this.goals;
    }

    public int getAId() {
        return this.a_id;
    }

    public int getTId() {
        return this.t_id;
    }

    public int getYearBorn() {
        return this.year_born;
    }

}
