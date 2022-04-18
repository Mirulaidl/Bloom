package com.example.bloom;

public class User {

    public String fullname, username, email, type, phone;

    public User(){

    }

    public User(String fullName, String username, String email, String type, String phone){
        this.fullname = fullName;
        this.username = username;
        this.email = email;
        this.type = type;
        this.phone = phone;
    }

    public String getType() {
        return type;
    }
}
