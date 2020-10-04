package com.google.firebase.udacity.friendlychat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizActivity extends AppCompatActivity {

    private TextView score,question,timer;
    private Button option1, option2, option3, option4,submit;
    private int correct,questionNo;
    private DatabaseReference databaseReference;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        correct = 0;
        questionNo = 0;
        score = findViewById(R.id.score);
        question = findViewById(R.id.question);
        timer = findViewById(R.id.timer);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        submit = findViewById(R.id.submit);
        newQuestion();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                newQuestion();
            }
        });
    }

    private void newQuestion() {
        questionNo++;
        if (questionNo < 4) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(questionNo));
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.getValue() != null) {
                        QuestionInfo questionInfo = snapshot.getValue(QuestionInfo.class);
                        option1.setBackgroundColor(Color.parseColor("#5A595B"));
                        option2.setBackgroundColor(Color.parseColor("#5A595B"));
                        option3.setBackgroundColor(Color.parseColor("#5A595B"));
                        option4.setBackgroundColor(Color.parseColor("#5A595B"));
                        question.setText(questionInfo.getQuestion());
                        option1.setText(questionInfo.getOption1());
                        option2.setText(questionInfo.getOption2());
                        option3.setText(questionInfo.getOption3());
                        option4.setText(questionInfo.getOption4());
                        startTimer();
                        onOptionsClicked(questionInfo);
                    }
                    else {
                        //Last question completed
                        // TODO: add code when last question is attempted
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(11000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("Time left: "+String.valueOf(millisUntilFinished/1000) + " secs");
            }

            @Override
            public void onFinish() {
                newQuestion();
            }
        };
        countDownTimer.start();
    }


    private void onOptionsClicked(QuestionInfo questionInfo) {

        final String correctAnswer = questionInfo.getAnswer();
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForAnswer(option1,correctAnswer);
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForAnswer(option2,correctAnswer);
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForAnswer(option3,correctAnswer);
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForAnswer(option4,correctAnswer);
            }
        });
    }

    private void checkForAnswer(final Button option, final String correctAnswer) {
        if(option.getText().toString().equals(correctAnswer)) {
            option.setBackgroundColor(Color.parseColor("#1ae317"));
            correct++;
            score.setText(String.valueOf(correct));
        }
        else {
            option.setBackgroundColor(Color.parseColor("#e31717"));
        }
    }

}