package dais.entities;

public class Pitch {
    Integer pitchID;
    Integer addressID;
    Integer capacity;
    String name;

    public Pitch(Integer pID, Integer aID, Integer c, String n) {
        this.pitchID = pID;
        this.addressID = aID;
        this.capacity = c;
        this.name = n;
    }

    public Integer getAddressID() {
        return this.addressID;
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public Integer getPitchID() {
        return this.pitchID;
    }

    public String getName() {
        return this.name;
    }
}
