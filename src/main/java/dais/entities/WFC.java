package dais.entities;

public class WFC {
    Integer id;
    Integer year;
    Integer a_id;

    public WFC(Integer id, Integer y, Integer a) {
        this.id = id;
        this.year = y;
        this.a_id = a;
    }

    public WFC(){};

    public Integer getId() {
        return this.id;
    }

    public Integer getYear() {
        return this.year;
    }

    public Integer getAId() {
        return this.a_id;
    }
}

