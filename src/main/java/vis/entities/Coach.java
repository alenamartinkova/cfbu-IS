package vis.entities;

import java.util.Date;

public class Coach {
    Integer id;
    String name;
    String sureName;
    Integer teamID;
    Date dateOfBirth;
    Integer covid;
    Date quarantinedFrom;
    String email;
    String license;

    public Coach(Integer id, Integer tID, String n, String sn, Date d, Integer c, Date q, String e, String l) {
        this.id = id;
        this.name = n;
        this.sureName = sn;
        this.teamID = tID;
        this.dateOfBirth = d;
        this.covid = c;
        this.quarantinedFrom = q;
        this.email = e;
        this.license = l;
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
        return this.license;
    }

    public String getEmail() {
        return this.email;
    }
}
