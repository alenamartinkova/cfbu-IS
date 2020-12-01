package vis.entities;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Player {
    Integer id;
    Integer teamID;
    String name;
    String sureName;
    Timestamp dateOfBirth;
    Integer covid;
    Timestamp quarantinedFrom;
    String email;
    String stick;

    public Player(Integer id, Integer tID, String n, String sn, String d, Integer c, String q, String e, String s) {
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

    public String getStick() {
        return this.stick;
    }

    public String getEmail() {
        return this.email;
    }
}
