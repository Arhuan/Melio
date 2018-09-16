package com.melio.cara.myapplication;

public class User {

    public String email;
    public String password;
    public Boolean isModerator;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email,String password) {
        this.email = email;
        this.password = password;
        this.isModerator=false;
    }

    public void setModeratorStatus(Boolean newStatus){
        this.isModerator = newStatus;
    }

}
