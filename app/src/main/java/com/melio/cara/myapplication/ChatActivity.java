package com.melio.cara.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {
    private String databaseRef;

    // TODO: add class to databaseRef, and implement getUser
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button sendButton = (Button) findViewById(R.id.button_chatbox_send);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText) findViewById(R.id.edittext_chatbox);

                // Read the input field and display the ChatMessage
                displayChatMessage(new ChatMessage(input.getText(), databaseRef.getUser()));

                // Clear the input after message has been sent
                input.setText("");
            }
        });
    }

    public void displayChatMessage() {

    }
}
