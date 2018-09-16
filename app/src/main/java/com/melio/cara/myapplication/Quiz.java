package com.melio.cara.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends AppCompatActivity {
    private String[] question = new String[]
            {"I was very anxious, worried or scared about a lot of things in my life.",
                    "I felt that my worry was out of my control.",
                    "I felt restless, agitated, frantic, or tense.",
                    "I had trouble sleeping - I could not fall or stay asleep, and/or didn't feel well-rested when I woke up.",
                    "My heart would skip beat, was pounding, or my heart rate increased.",
                    "I was sweating profusely.",
                    "My hands, legs or entire body were shaking, trembling, or felt tingly.",
                    "I had difficulty breathing or swallowing.",
                    "I had pain in my chest, almost like I was having a heart attack.",
                    "I felt sick to my stomach, like I was going to throw up.",
                    "I felt dizzy, my head was spinning, or felt like I was going to faint.",
                    "I was scared that I would lose control, go crazy, or die."
            };

    public int x = 0;
    private TextView questionText;
    private RadioGroup radioGroup;
    private int selected = -1;
    private int normal = 2131230858;
    private updateAnswers uAnswers;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseRef = FirebaseDatabase.getInstance().getReference("users");
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        update(selected);

        radioGroup = findViewById(R.id.radioGroup);
        Button nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioGroup.getCheckedRadioButtonId()!= -1)
                {
                    selected = radioGroup.getCheckedRadioButtonId() - normal;
                    Log.d("Quiz.java TESTING:   ", "checked button: " + selected);
                    update(selected);
                }
            }
        });
    }

    public void update(int picked){
        if(x < question.length){
            questionText.setText(question[x]);
            if (picked != -1){
                uAnswers = new Quiz.updateAnswers(picked, x);
                uAnswers.execute((Void) null);
                radioGroup.clearCheck();
                x++;
            }
        }
        else{
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }
    }

    public class updateAnswers extends AsyncTask<Void, Void, Boolean> {
        //Log.v("msg"","Hello")
        private int answers;
        private String index;

        updateAnswers(int inpAnswers, int fax) {
            answers = inpAnswers;
            index = String.valueOf(fax);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Query query = databaseRef.child(localUsernameSingleton.getKnownInstance().LocalUsername);
            query.addListenerForSingleValueEvent(new ValueEventListener(){

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        databaseRef.child(localUsernameSingleton.getKnownInstance().LocalUsername).child("answers").child(index).setValue(answers);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {


                }
            });

            return true;
        }


        @Override
        protected void onPostExecute(final Boolean success) {
        }

        @Override
        protected void onCancelled() {
        }
    }
}

