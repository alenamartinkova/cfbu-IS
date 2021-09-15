package org.alenamartinkova.dais.DTO;

import org.alenamartinkova.dais.business.Pitch;

public class PitchDTO {
    Integer pitchID;
    Integer addressID;
    Integer capacity;
    String name;

    public PitchDTO() {}
    public PitchDTO(Integer pID, Integer aID, Integer c, String n) {
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

    public Pitch toBO() {
        return new Pitch(this.pitchID, this.addressID, this.capacity, this.name);
    }
}
