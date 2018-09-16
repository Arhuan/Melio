package com.melio.cara.myapplication;

public class User {

    public String username;
    public String password;
    public Boolean isModerator;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username,String password) {
        this.username = username;
        this.password = password;
        this.isModerator=false;
    }

    public void setModeratorStatus(Boolean newStatus){
        this.isModerator = newStatus;
    }

}
