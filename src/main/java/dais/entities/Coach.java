package dais.entities;

import java.util.Date;

public class Coach {
    Integer id;
    String name;
    String sureName;
    Integer teamID;
    Date dateOfBirth;
    Boolean covid;
    Date quarantinedFrom;
    String email;
    String license;

    public Coach(Integer id, String n, String sn, Integer tID, Date d, Boolean c, Date q, String e, String s) {
        this.id = id;
        this.name = n;
        this.sureName = sn;
        this.teamID = tID;
        this.dateOfBirth = d;
        this.covid = c;
        this.quarantinedFrom = q;
        this.email = e;
        this.license = s;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getCovid() {
        return this.covid;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Date getQuarantinedFrom() {
        return this.quarantinedFrom;
    }

    public Integer getTeamID() {
        return this.teamID;
    }

    public String getSureName() {
        return this.sureName;
    }

    public String getStick() {
        return this.license;
    }

    public String getEmail() {
        return this.email;
    }
}
