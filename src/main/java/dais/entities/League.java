package dais.entities;

public class League {
    Integer id;
    Integer division;
    String name;

    public League(Integer id, Integer d, String n) {
        this.id = id;
        this.division = d;
        this.name = n;
    }

    public League(){};

    public Integer getId() {
        return this.id;
    }

    public Integer getDivision() {
        return this.division;
    }

    public String getName() {
        return this.name;
    }
}

