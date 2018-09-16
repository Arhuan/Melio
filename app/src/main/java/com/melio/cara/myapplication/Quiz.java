package com.melio.cara.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

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

    private int[] answers = new int[12];
    private int x = 0;
    private TextView questionText;
    private RadioGroup radioGroup;
    private int selected = -1;
    private int normal = 2131230848;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        update();

        radioGroup = findViewById(R.id.radioGroup);
        Button nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioGroup.getCheckedRadioButtonId()!= -1)
                {
                    selected = radioGroup.getCheckedRadioButtonId() - normal;
                    Log.d("Quiz.java TESTING:   ", "checked button: " + selected);
                    x++;
                    update();
                }
            }
        });
    }

    public void update(){
        if(x < question.length){
            questionText.setText(question[x]);
            if (selected != -1){
                answers[x] = selected;
                radioGroup.clearCheck();
            }
            Log.d("Quiz.java TESTING:   ", "answers: " + answers);
        }
        else{
            Log.d("Quiz.java TESTING:   ", "answers: " + answers);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
