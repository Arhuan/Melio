package com.melio.cara.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {
    private DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
    private FirebaseListAdapter<ChatMessage> adapter;

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
                databaseRef.push().setValue(new ChatMessage(input.getText(), something));

                // Clear the input after message has been sent
                input.setText("");
            }
        });

        displayChatMessage();
    }

    public void displayChatMessage() {
        ListView listOfMessages = (ListView) findViewById(R.id.listview_message_list);

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView) v.findViewById(R.id.message_body);
                TextView messageUser = (TextView) v.findViewById(R.id.message_name);
                TextView messageTime = (TextView) v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getBody());
                messageUser.setText(model.getSender().getEmail());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getTimeSent()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }

}
