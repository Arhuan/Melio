package com.melio.cara.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz extends AppCompatActivity {
    private String[] question = new String[]
            {
                    "I had difficulty concentrating.",
                    "I had difficulty sleeping. It was hard to fall or stay asleep.",
                    "I had difficulty breathing or swallowing.",
                    "I had difficulty getting out of bed.",
                    "My limbs would shake, tremble, or tingle.",
                    "I felt restless, agitated, or tense.",
                    "I felt like a burden.",
                    "I felt pain in my chest, almost like I was having a heart attack.",
                    "I felt sick to my stomach, almost like I was going to throw up.",
                    "I felt dizzy, almost like I was going to faint.",
                    "I felt very anxious, worried or scared about things in my life.",
                    "I felt an overwhelming urge to drink alcohol or abuse other substances."
            };

    public int x = 0;
    private TextView questionText;
    private RadioGroup radioGroup;
    private int selected = -1;
    private int normal = 2131230858;
    private int[] results = new int[12];
    private updateRecommendations mRecommendations;
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
                results[x] = picked;
                radioGroup.clearCheck();
                x++;
            }
        }
        else{
            recommendations();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }


    public void recommendations(){
        float PTSD = 0;
        float depression = 0;
        float anxiety = 0;
        float substance = 0;
        float suicidal = 0;

        for (int m = 0; m < 12; m++){
            if (m == 0) {
                PTSD += results[m]/3;
                substance += results[m]/3;
            }
            else if (m == 1){
                PTSD += results[m]/5;
                substance += results[m]/5;
                depression += results[m]/5;
                anxiety += results[m]/5;
                suicidal += results[m]/5;
            }
            else if (m == 2){
                PTSD += results[m]/3;
                anxiety += results[m]/3;
            }
            else if (m == 3){
                depression += results[m]/2;
            }
            else if (m == 4){
                substance += results[m]/3;
                anxiety += results[m]/3;
            }
            else if (m == 5){
                PTSD += results[m]/3;
                suicidal += results[m]/3;
            }
            else if (m == 6){
                depression += results[m]/3;
                suicidal += results[m]/3;
            }
            else if (m == 7){
                depression += results[m]/3;
                suicidal += results[m]/3;
            }
            else if (m == 8){
                depression += results[m]/3;
                substance += results[m]/3;
            }
            else if (m == 9){
                anxiety += results[m]/3;
                substance += results[m]/3;
            }
            else if (m == 10){
                anxiety += results[m]/2;
            }
            else if (m == 11){
                substance += results[m]/2;
            }
        }

        ArrayList<Float> sorting = new ArrayList<>();
        sorting.add(PTSD);
        sorting.add(depression);
        sorting.add(anxiety);
        sorting.add(substance);
        sorting.add(suicidal);

        Log.d("testttt", "lisst: " + sorting);

        String msg = "Here are your results: \t" + "PTSD: " + PTSD + " Depression: " + depression + " Anxiety: " + anxiety + " Substance Abuse: " + substance + " Suicidal: " + suicidal;
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

        mRecommendations = new updateRecommendations(sorting);
        mRecommendations.execute((Void) null);
    }



    public class updateRecommendations extends AsyncTask<Void, Void, Boolean> {
        private ArrayList<Float> answers;

        updateRecommendations(ArrayList<Float> inpAnswers) {
            answers = inpAnswers;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Query query = databaseRef.child(localUsernameSingleton.getKnownInstance().LocalUsername);
            query.addListenerForSingleValueEvent(new ValueEventListener(){

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        for (int m = 0; m < 5; m++){
                            String index = String.valueOf(m);
                            databaseRef.child(localUsernameSingleton.getKnownInstance().LocalUsername).child("answers").child(index).setValue(answers.get(m));
                        }
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

