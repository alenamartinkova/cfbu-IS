package vis.business;

import java.sql.Timestamp;

public class Referee {
    Integer refereeID;
    String name;
    String sureName;
    String email;
    Timestamp dateOfBirth;

    public Referee(Integer rID, String n, String sn, String e, String d) {
        this.refereeID = rID;
        this.name = n;
        this.sureName = sn;
        this.dateOfBirth = Timestamp.valueOf(d);
        this.email = e;
    }

    public String getName() {
        return this.name;
    }

    public Timestamp getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Integer getRefereeID() {
        return this.refereeID;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSureName() {
        return this.sureName;
    }
}
