package com.programming.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class QuizType extends AppCompatActivity {

    private String selectedTopicName = "";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_type);


        final LinearLayout java = findViewById(R.id.javaLayout);
        final LinearLayout php = findViewById(R.id.phpLayout);
        final LinearLayout html = findViewById(R.id.htmlLayout);
        final LinearLayout android = findViewById(R.id.androidLayout);

        final Button startBtn = findViewById(R.id.startQuizBtn);
        final Button logoutButton = findViewById(R.id.logoutButton);



        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "java";

                java.setBackgroundResource(R.drawable.round_back_white_stroke10);

                php.setBackgroundResource(R.drawable.round_red_white10);

                html.setBackgroundResource(R.drawable.round_red_white10);

                android.setBackgroundResource(R.drawable.round_red_white10);

            }
        });

        php.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "php";
                php.setBackgroundResource(R.drawable.round_back_white_stroke10);

                java.setBackgroundResource(R.drawable.round_red_white10);

                html.setBackgroundResource(R.drawable.round_red_white10);

                android.setBackgroundResource(R.drawable.round_red_white10);

            }
        });

        html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "html";

                html.setBackgroundResource(R.drawable.round_back_white_stroke10);

                php.setBackgroundResource(R.drawable.round_red_white10);

                java.setBackgroundResource(R.drawable.round_red_white10);

                android.setBackgroundResource(R.drawable.round_red_white10);

            }
        });

        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "android";

                android.setBackgroundResource(R.drawable.round_back_white_stroke10);

                php.setBackgroundResource(R.drawable.round_red_white10);

                html.setBackgroundResource(R.drawable.round_red_white10);

                java.setBackgroundResource(R.drawable.round_red_white10);

            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTopicName.isEmpty()){

                    Toast.makeText(QuizType.this,"Please select the topic",Toast.LENGTH_LONG).show();

                }else{

                    Intent intent = new Intent(QuizType.this,QuizActivity.class);
                    intent.putExtra("selectedTopic",selectedTopicName);
                    startActivity(intent);

                }
            }
        });

        mAuth = FirebaseAuth.getInstance();

        logoutButton.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(QuizType.this, MainActivity.class));
        });
    }
}