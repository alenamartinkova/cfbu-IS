package dais.entities;

import java.util.Date;

public class Player {
    Integer id;
    String name;
    String sureName;
    Integer teamID;
    Date dateOfBirth;
    Boolean covid;
    Date quarantinedFrom;
    String email;
    Character stick;

    public Player(Integer id, String n, String sn, Integer tID, Date d, Boolean c, Date q, String e, Character s) {
        this.id = id;
        this.name = n;
        this.sureName = sn;
        this.teamID = tID;
        this.dateOfBirth = d;
        this.covid = c;
        this.quarantinedFrom = q;
        this.email = e;
        this.stick = s;
    }

    public Player(){};

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

    public Character getStick() {
        return this.stick;
    }

    public String getEmail() {
        return this.email;
    }
}
