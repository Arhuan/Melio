package com.melio.cara.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModActivity extends AppCompatActivity {
    private DatabaseReference databaseRef;
    private ArrayAdapter<Post> arrayAdapter;
    private ArrayList<Post> PostList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ListView postList = (ListView)findViewById(R.id.pendingPosts);

        getPosts();

        ArrayAdapter<Post> arrayAdapter = new ArrayAdapter<Post>(this, android.R.layout.simple_list_item_1, (List<Post>) postList);
        postList.setAdapter(arrayAdapter);

        postList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = postList.getItemAtPosition(position);
                Post p = (Post) o;

               }
        });

        FloatingActionButton returnButton = (FloatingActionButton) findViewById(R.id.returnMain);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        databaseRef = FirebaseDatabase.getInstance().getReference();
    }

    protected void getPosts(){
        Query query = databaseRef.child("posts").orderByChild("postdate").equalTo(false);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot posts: dataSnapshot.getChildren()){
                        Post posting = posts.getValue(Post.class);
                        PostList.add(posting);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}
