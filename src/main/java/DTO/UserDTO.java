package DTO;

import java.sql.Timestamp;

public class UserDTO {
    Integer userID;
    String name;
    String sureName;
    String email;
    String login;
    String password;
    Integer isAdmin;
    Timestamp createdAt;
}
