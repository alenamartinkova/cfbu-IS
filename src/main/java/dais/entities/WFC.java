package dais.entities;

public class WFC {
    int id;
    int year;
    int a_id;

    public WFC(int id, int y, int a) {
        this.id = id;
        this.year = y;
        this.a_id = a;
    }

    public int getId() {
        return this.id;
    }

    public int getYear() {
        return this.year;
    }

    public int getAId() {
        return this.a_id;
    }
}

