package com.melio.cara.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    private DatabaseReference databaseRef;
    ListView forumposts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button chatButton = findViewById(R.id.roomButton);
        Button postButton = findViewById(R.id.PostAdd);
        //getPosts();
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PostActivity.class));
            }
        });
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RoomActivity.class));
            }
        });

// Write a message to the database
        databaseRef = FirebaseDatabase.getInstance().getReference();
    }

    protected void getPosts(){
        int numPosts = 5;

        Query query = databaseRef.child("posts").limitToFirst(numPosts);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int pos = 0;
                String[] Headers = null;
                String[]  Bodies = null;
                if (dataSnapshot.exists()){
                    for (DataSnapshot Post: dataSnapshot.getChildren()){
                        Post databasePost = Post.getValue(Post.class);
                        Headers[pos] = databasePost.getHeader();
                        Bodies[pos] = databasePost.getBody();

                        pos++;
                    }
                    ForumList listAdapter = new ForumList(MainActivity.this,Headers,Bodies);
                    forumposts = (ListView)findViewById(R.id.forum);
                    forumposts.setAdapter(listAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }

}
