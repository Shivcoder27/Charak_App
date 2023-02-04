package com.example.hackthisfall;

public class User {
    String email;
    String password;
    String fullname;
    String contact ;
    String registerationID;
    String passoutBatch;
    String specialization;

    public User(String email, String password, String fullname, String contact, String registerationID, String passoutBatch, String specialization) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.contact = contact;
        this.registerationID = registerationID;
        this.passoutBatch = passoutBatch;
        this.specialization = specialization;
    }
    public User(){

    }
}
