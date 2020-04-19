package dais.entities;

public class League {
    int id;
    int division;
    String name;

    public League(int id, int d, String n) {
        this.id = id;
        this.division = d;
        this.name = n;
    }

    public int getId() {
        return this.id;
    }

    public int getDivision() {
        return this.division;
    }

    public String getName() {
        return this.name;
    }
}

