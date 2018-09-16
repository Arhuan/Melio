package com.melio.cara.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {
    private List<Room> rooms;
    private ListView roomList;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.roomList = findViewById(R.id.listview_room_list);
        this.rooms = new ArrayList<Room>();
        initializeRooms();

        RoomsAdapter roomsAdapter = new RoomsAdapter(this, this.rooms);
        this.roomList.setAdapter(roomsAdapter);
    }

    public void enterChat(View view) {
        Intent chatIntent = new Intent(this, ChatActivity.class);
        startActivity(chatIntent);
    }

    private void initializeRooms() {
        this.rooms.add(new Room("First Responders"));
        this.rooms.add(new Room("Stigma"));
        this.rooms.add(new Room("Alcohol"));
        this.rooms.add(new Room("Drugs"));
        this.rooms.add(new Room("Depression"));

        for (int i = 0; i < this.rooms.size(); i++) {
            this.databaseReference.child("rooms").child(this.rooms.get(i).getTitle()).setValue("xd");
        }
    }
}
