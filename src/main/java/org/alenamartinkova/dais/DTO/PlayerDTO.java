package org.alenamartinkova.dais.DTO;

import org.alenamartinkova.dais.business.Player;

import java.sql.Timestamp;

public class PlayerDTO {
    Integer id;
    Integer teamID;
    String name;
    String sureName;
    Timestamp dateOfBirth;
    Integer covid;
    Timestamp quarantinedFrom;
    String email;
    String stick;

    public PlayerDTO() {}
    public PlayerDTO(Integer id, Integer tID, String n, String sn, String d, Integer c, String q, String e, String s) {
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

    public Player toBO() {
        return new Player(this.id, this.teamID, this.name, this.sureName, this.dateOfBirth.toString(), this.covid, this.quarantinedFrom == null?null: this.quarantinedFrom.toString(), this.email, this.stick);
    }
}
