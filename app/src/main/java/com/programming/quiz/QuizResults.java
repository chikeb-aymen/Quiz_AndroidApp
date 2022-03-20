package com.programming.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuizResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        TextView score = findViewById(R.id.score);
        score.setText(String.valueOf(getIntent().getIntExtra("score",0)));

        TextView quizSize = findViewById(R.id.quizSize);
        quizSize.setText(String.valueOf(" / "+getIntent().getIntExtra("quizSize",6)));

        AppCompatButton startNewQuizBtn = findViewById(R.id.startNewQuizBtn);
        startNewQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizResults.this,QuizType.class));
                finish();
            }
        });

    }
}