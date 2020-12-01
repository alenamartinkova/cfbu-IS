package vis.entities;

import java.sql.Timestamp;

public class User {
    Integer userID;
    String name;
    String sureName;
    String email;
    String login;
    String password;
    Integer isAdmin;
    Timestamp createdAt;

    public User(Integer uID, String n, String sn, String e, String l, String p, Integer iA, String cA) {
        this.userID = uID;
        this.name = n;
        this.sureName = sn;
        this.email = e;
        this.login = l;
        this.password = p;
        this.isAdmin = iA;
        this.createdAt = Timestamp.valueOf(cA);
    }

    public Integer getIsAdmin() {
        return this.isAdmin;
    }

    public Integer getUserID() {
        return this.userID;
    }

    public String getEmail() {
        return this.email;
    }

    public String getLogin() {
        return this.login;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSureName() {
        return this.sureName;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }
}
