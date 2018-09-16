package com.melio.cara.myapplication;

public class emailSingleton {

    private static emailSingleton instance;

    private String LocalEmail;

    private emailSingleton(){
        LocalEmail = "";
    }

    public static emailSingleton getInstance(String username){
        if (instance == null){
            instance = new emailSingleton();
            instance.LocalEmail =  username;
        }
        return instance;
    }

    public String getLocalEmail(){
        return LocalEmail;
    }

}
