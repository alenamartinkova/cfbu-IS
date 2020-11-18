package dais.entities;

import java.util.Date;

public class Player {
    Integer id;
    Integer teamID;
    String name;
    String sureName;
    Date dateOfBirth;
    Integer covid;
    Date quarantinedFrom;
    String email;
    String stick;

    public Player(Integer id, Integer tID, String n, String sn, Date d, Integer c, Date q, String e, String s) {
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

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getCovid() {
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
        return this.stick;
    }

    public String getEmail() {
        return this.email;
    }
}
