package com.melio.cara.myapplication;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {

    public String username;
    public String password;
    public Boolean isModerator;
    public ArrayList<Integer> answers = new ArrayList<>();

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username,String password) {
        for(int x = 1; x<=5; x++){
            answers.add(x);
        }
        this.username = username;
        this.password = password;
        this.isModerator=false;
    }


    public void setModeratorStatus(Boolean newStatus){
        this.isModerator = newStatus;
    }

}
