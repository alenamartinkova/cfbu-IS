package dais.entities;

import java.util.Date;

public class Referee {
    Integer refereeID;
    String name;
    String sureName;
    Date dateOfBirth;
    String email;

    Referee(Integer rID, String n, String sn, Date d, String e) {
        this.refereeID = rID;
        this.name = n;
        this.sureName = sn;
        this.dateOfBirth = d;
        this.email = e;
    }

    public String getName() {
        return this.name;
    }

    public Date getDateOfBirth() {
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
