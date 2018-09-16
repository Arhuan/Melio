package com.melio.cara.myapplication;

import java.util.Date;

/**
 * Created by aahuangg on 2018-09-15.
 */

public class ChatMessage {
    private String body;
    private String sender;
    private long timeSent;

    public ChatMessage(String text, String sender) {
        this.body = text;
        this.sender = sender;
        this.timeSent = new Date().getTime();
    }

    public void setBody(String text) {
        this.body = text;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setTimeSent(long time) {
        this.timeSent = time;
    }

    public String getBody() {
        return this.body;
    }

    public String getSender() {
        return this.sender;
    }

    public long getTimeSent() {
        return this.timeSent;
    }
}
