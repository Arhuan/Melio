package com.melio.cara.myapplication;

/**
 * Created by aahuangg on 2018-09-16.
 */

public class Room {
    private String title;

    public Room() {
        // Default constructor required for calls to DataSnapshot.getValue(Room.class)
    }

    public Room(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
