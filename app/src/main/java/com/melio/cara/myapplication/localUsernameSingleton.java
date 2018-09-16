package com.melio.cara.myapplication;

public class localUsernameSingleton {

    private static localUsernameSingleton instance;

    private String LocalUsername;

    private localUsernameSingleton(){
        LocalUsername = "";
    }

    public static localUsernameSingleton getInstance(String username){
        if (instance == null){
            instance = new localUsernameSingleton();
            instance.LocalUsername =  username;
        }
        return instance;
    }

    public String getLocalUsername(){
        return LocalUsername;
    }

}
