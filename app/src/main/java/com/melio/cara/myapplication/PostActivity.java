package com.melio.cara.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class PostActivity extends AppCompatActivity {

    private TextInputEditText inpHead;
    private TextInputEditText inpBody;

    private DatabaseReference databaseRef;

    private writePost wPost = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        inpHead = findViewById(R.id.HeaderBox);
        inpBody = findViewById(R.id.BodyBox);

        databaseRef = FirebaseDatabase.getInstance().getReference();
        Button createPostButton = findViewById(R.id.CreatePostButton);
        createPostButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                createPost();
            }
        });
    }

    public void createPost(){
        String header = inpHead.getText().toString();
        String body = inpBody.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (header.isEmpty()) {
            inpHead.setError(getString(R.string.error_invalid_header));
            focusView = inpHead;
            cancel = true;
        }

        // Check for a valid email address.
        if (body.isEmpty()) {
            inpBody.setError(getString(R.string.error_invalid_body));
            focusView = inpBody;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            wPost = new writePost(header, body);
            wPost.execute((Void) null);
        }
    }

    public class writePost extends AsyncTask<Void, Void, Boolean> {

        private final String Header;
        private final String Body;

        writePost(String Header, String Body) {
            this.Header = Header;
            this.Body = Body;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Query query = databaseRef.child("posts");
            query.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Post newPost = new Post(Header,Body);
                        databaseRef.child("posts").child(new Date().toString()).setValue(newPost);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            return true;
        }



        @Override
        protected void onPostExecute(final Boolean success) {
            wPost = null;
            //showProgress(false);

            if (success) {
                finish();
            } else {
                inpBody.setError(getString(R.string.error_invalid_body));
                inpBody.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            wPost = null;
        }
    }
}