package dais.entities;

public class League {
    Integer id;
    Integer category;
    String name;

    public League(Integer id, Integer c, String n) {
        this.id = id;
        this.category = c;
        this.name = n;
    }

    public League(){};

    public Integer getId() {
        return this.id;
    }

    public Integer getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }
}

