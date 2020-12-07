package business;

import java.sql.Timestamp;

public class Coach {
    Integer id;
    String name;
    String sureName;
    Integer teamID;
    Timestamp dateOfBirth;
    Integer covid;
    Timestamp quarantinedFrom;
    String email;
    String license;

    public Coach(Integer id, Integer tID, String n, String sn, String d, Integer c, String q, String e, String l) {
        this.id = id;
        this.name = n;
        this.sureName = sn;
        this.teamID = tID;
        this.dateOfBirth = Timestamp.valueOf(d);
        this.covid = c;
        try {
            this.quarantinedFrom = Timestamp.valueOf(q);
        } catch (IllegalArgumentException ex) {
            this.quarantinedFrom = null;
        }
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

    public Timestamp getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Timestamp getQuarantinedFrom() {
        return this.quarantinedFrom;
    }

    public Integer getTeamID() {
        return this.teamID;
    }

    public String getSureName() {
        return this.sureName;
    }

    public String getLicense() {
        return this.license;
    }

    public String getEmail() {
        return this.email;
    }
}
