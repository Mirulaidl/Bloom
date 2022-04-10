package com.example.bloom;

public class User {

    public String fullname, username, email, type;

    public User(){

    }

    public User(String fullName, String username, String email, String type){
        this.fullname = fullName;
        this.username = username;
        this.email = email;
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
