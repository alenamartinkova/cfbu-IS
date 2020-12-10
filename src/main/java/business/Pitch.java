package business;

import DTO.PitchDTO;
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

    public PitchDTO toDTO() {
        PitchDTO pitchDTO = new PitchDTO(this.pitchID, this.addressID, this.capacity, this.name);
        return pitchDTO;
    }

    public static ArrayList<Pitch> arrayListToBO(ArrayList<PitchDTO> pitchDTOS) {
        ArrayList<Pitch> p = new ArrayList<>();

        for(int i = 0; i < pitchDTOS.size(); i++) {
            p.add(pitchDTOS.get(i).toBO());
        }
        return p;
    }
}
