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
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ChatActivity extends AppCompatActivity {
    private DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
    private FirebaseListAdapter<ChatMessage> adapter;
    private localUsernameSingleton testObject = localUsernameSingleton.getInstance("aaron");

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
                localUsernameSingleton username = localUsernameSingleton.getKnownInstance();
                databaseRef.child("Message").push().setValue(new ChatMessage(input.getText().toString(), username.LocalUsername));

                // Clear the input after message has been sent
                input.setText("");
            }
        });

        displayChatMessage();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.adapter.stopListening();
    }

    public void displayChatMessage() {
        ListView listMessages = (ListView) findViewById(R.id.listview_message_list);

        Query query = this.databaseRef.child("Message");

        FirebaseListOptions<ChatMessage> options = new FirebaseListOptions.Builder<ChatMessage>()
                .setQuery(query, ChatMessage.class)
                .setLayout(R.layout.item_message_received)
                .build();

        this.adapter = new FirebaseListAdapter<ChatMessage>(options) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView) v.findViewById(R.id.message_body);
                TextView messageUser = (TextView) v.findViewById(R.id.message_name);
                TextView messageTime = (TextView) v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getBody());
                messageUser.setText(model.getSender());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getTimeSent()));
            }
        };

        listMessages.setAdapter(this.adapter);
    }

}
