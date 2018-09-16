package com.melio.cara.myapplication;

public class localUsernameSingleton {

    private static localUsernameSingleton instance;

    public String LocalUsername;

    private localUsernameSingleton(String username){
        LocalUsername = username;
    }

    public static localUsernameSingleton getInstance(String username){
        if (instance == null){
            instance = new localUsernameSingleton(username);
        }
        return instance;
    }
}