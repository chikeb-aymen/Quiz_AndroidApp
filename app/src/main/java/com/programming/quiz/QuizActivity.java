package com.programming.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {

    private TextView questions;
    private TextView question;

    private AppCompatButton option1,option2,option3,option4;

    private AppCompatButton nextBtn;

    private Timer quizTimer;

    private int totalTimeInMins = 1;

    private int seconds = 0;

    public List<Questions> questionsList = new ArrayList<>();

    public int currentQuestionsIndex = 0;

    public String selectedOptionByUser = "";

    private int correctAnswers = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView timer = findViewById(R.id.timer);
        final TextView selectedTopicName = findViewById(R.id.topicName);

        question = findViewById(R.id.question);
        questions = findViewById(R.id.questions);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        nextBtn = findViewById(R.id.nextBtn);


        final String getSelectedTopicName = getIntent().getStringExtra("selectedTopic");

        selectedTopicName.setText(getSelectedTopicName);


        //questionsList = QuestionsData.getQuestion(getSelectedTopicName);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Attach a listener to read the data at our posts reference


        db.collection("questions").whereEqualTo("topic",getSelectedTopicName).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Questions question = new Questions(
                                        document.getString("question"),
                                        document.getString("option1"),
                                        document.getString("option2"),
                                        document.getString("option3"),
                                        document.getString("option4"),
                                        document.getString("answer"),
                                        "");

                                questionsList.add(question);

                                System.out.println( document.getString("question"));
                            }

                            if(questionsList.size()>0){
                                questions.setText((currentQuestionsIndex+1)+"/"+questionsList.size());
                                question.setText(questionsList.get(0).getQuestion());
                                option1.setText(questionsList.get(0).getOption1());
                                option2.setText(questionsList.get(0).getOption2());
                                option3.setText(questionsList.get(0).getOption3());
                                option4.setText(questionsList.get(0).getOption4());

                                startTimer(timer);
                            }else{
                                Toast.makeText(QuizActivity.this,"We don't have any question in this topic",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(QuizActivity.this,QuizType.class));
                            }


                        } else {
                            System.out.println("Error getting documents."+task.getException());
                        }
                    }
        });





        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option1.getText().toString();
                    option1.setBackgroundResource(R.drawable.round_back_red10);
                    option1.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsList.get(currentQuestionsIndex).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option2.getText().toString();
                    option2.setBackgroundResource(R.drawable.round_back_red10);
                    option2.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsList.get(currentQuestionsIndex).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option3.getText().toString();
                    option3.setBackgroundResource(R.drawable.round_back_red10);
                    option3.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsList.get(currentQuestionsIndex).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option4.getText().toString();
                    option4.setBackgroundResource(R.drawable.round_back_red10);
                    option4.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsList.get(currentQuestionsIndex).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedOptionByUser.isEmpty()){
                    Toast.makeText(QuizActivity.this,"Please select an option",Toast.LENGTH_SHORT).show();
                }
                else{
                    changeNextQuestion();
                }

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                quizTimer.purge();
                quizTimer.cancel();
                startActivity(new Intent(QuizActivity.this,QuizType.class));
                finish();

            }
        });
    }

    private void startTimer(TextView timerTextView){
        quizTimer = new Timer();
        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(seconds == 0){

                    totalTimeInMins--;
                    seconds = 59;

                }
                else if(seconds == 0 && totalTimeInMins == 0){

                    quizTimer.purge();
                    quizTimer.cancel();
                    //quizTimer = null;

                    Toast.makeText(QuizActivity.this,"Timer Over",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuizActivity.this,QuizResults.class);
                    intent.putExtra("correct",getCorrectAnswers());
                    intent.putExtra("incorrect",getIncorrectAnswers());
                    startActivity(intent);
                    finish();

                }else{
                    seconds--;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String finalMinutes = String.valueOf(totalTimeInMins);
                        String finalSeconds = String.valueOf(seconds);

                        //Ex 1 -> 01
                        if(finalMinutes.length() == 1){
                            finalMinutes = "0"+finalMinutes;
                        }

                        //Ex 9 -> 09
                        if(finalSeconds.length() == 1){
                            finalSeconds = "0"+finalSeconds;
                        }

                        timerTextView.setText(finalMinutes +":"+finalSeconds);


                    }
                });
            }
        },1000,1000);

    }

    private int getCorrectAnswers(){

        for (int i = 0; i < questionsList.size(); i++) {
            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsList.get(i).getAnswer();
            if(getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

    private int getIncorrectAnswers(){

        for (int i = 0; i < questionsList.size(); i++) {
            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsList.get(i).getAnswer();
            if(getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers--;
            }
        }
        return correctAnswers;
    }

    @Override
    public void onBackPressed() {
        quizTimer.purge();
        quizTimer.cancel();
        startActivity(new Intent(QuizActivity.this,MainActivity.class));
        finish();
    }

    private void revealAnswer(){
        final String getAnswer = questionsList.get(currentQuestionsIndex).getAnswer();
        if(option1.getText().equals(getAnswer)){
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(Color.WHITE);
        }
        else if(option2.getText().toString().equals(getAnswer)){
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(Color.WHITE);
        }
        else if(option3.getText().toString().equals(getAnswer)){
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(Color.WHITE);
        }else{
            option4.setBackgroundResource(R.drawable.round_back_green10);
            option4.setTextColor(Color.WHITE);
        }
    }

    private void changeNextQuestion(){
        currentQuestionsIndex++;
        if((currentQuestionsIndex+1)==questionsList.size()){
            nextBtn.setText("Submit Quiz");
        }

        if(currentQuestionsIndex < questionsList.size()){
            selectedOptionByUser = "";
            option1.setBackgroundResource(R.drawable.round_back_white_stroke10);
            option1.setTextColor(Color.parseColor("#1F6888"));

            option2.setBackgroundResource(R.drawable.round_back_white_stroke10);
            option2.setTextColor(Color.parseColor("#1F6888"));

            option3.setBackgroundResource(R.drawable.round_back_white_stroke10);
            option3.setTextColor(Color.parseColor("#1F6888"));

            option4.setBackgroundResource(R.drawable.round_back_white_stroke10);
            option4.setTextColor(Color.parseColor("#1F6888"));

            questions.setText((currentQuestionsIndex+1)+"/"+questionsList.size());
            question.setText(questionsList.get(currentQuestionsIndex).getQuestion());
            option1.setText(questionsList.get(currentQuestionsIndex).getOption1());
            option2.setText(questionsList.get(currentQuestionsIndex).getOption2());
            option3.setText(questionsList.get(currentQuestionsIndex).getOption3());
            option4.setText(questionsList.get(currentQuestionsIndex).getOption4());

        }else{
            Intent intent = new Intent(QuizActivity.this,QuizResults.class);
            intent.putExtra("score",getCorrectAnswers());
            intent.putExtra("quizSize",questionsList.size());
            startActivity(intent);

            finish();
        }
    }
}