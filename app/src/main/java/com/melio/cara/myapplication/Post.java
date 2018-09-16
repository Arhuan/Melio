package com.melio.cara.myapplication;


public class Post {
    private String header;
    private String body;

    public Post(String header, String body){
        this.header = header;
        this.body = body;
    }

    public Post(){}

    public String getHeader(){
        return this.header;
    }

    public String getBody(){
        return this.body;
    }

    public void updateHeader(String header){
        this.header = header;
    }

    public void updateBody(String body){
        this.body = body;
    }
}
