package com.melio.cara.myapplication;

import java.util.Date;

public class Post {
    private User poster;
    private String header;
    private String body;
    boolean approval;
    Date postdate;

    public Post(User poster, String header, String body){
        this.poster = poster;
        this.header = header;
        this.body = body;
        this.approval = false;
        this.postdate = new Date();
    }

    public Post(){}

    public String getHeader(){
        return this.header;
    }

    public String getBody(){
        return this.body;
    }

    public void setApproval(boolean approval){
        this.approval = approval;
    }

    public void updateHeader(String header){
        this.header = header;
    }

    public void updateBody(String body){
        this.body = body;
    }
}
