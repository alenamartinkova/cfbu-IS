package business;

import gateways.PitchGateway;

import java.sql.SQLException;
import java.util.ArrayList;

public class Pitch {
    Integer pitchID;
    Integer addressID;
    Integer capacity;
    String name;

    public Pitch(){};
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

    /**
     * Function that returns all pitches
     * @return
     * @throws SQLException
     */
    public static ArrayList<Pitch> fetch() throws SQLException {
        return PitchGateway.fetch();
    }
}
