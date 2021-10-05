package com.example.education;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DoFastMaths extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    private static DoFastMaths obDoFastMaths;


    Timer obTimer;
    SavingData obSaveData;
    Calculator obCalculator;

    Toolbar toolbar;

    TextView question, correct_wrong_answer, score, timer_text;
    Button option1, option2, option3, option4, nextButton;
    String program_expression;
    int resultOfExpression;

    int level;

    int startTime;
    long timeInMillis;
    CountDownTimer timer;

    public static DoFastMaths getInstance() {
        return obDoFastMaths;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_fast_maths);
        try {
            toolbar = findViewById(R.id.toolbar);
            level = getIntent().getIntExtra("LEVEL", 0);
            obDoFastMaths = this;
            obTimer = new Timer();
            obSaveData = new SavingData();
            obCalculator = new Calculator();
            nextButton = findViewById(R.id.next_button);
            question = findViewById(R.id.question);
            option1 = findViewById(R.id.option1);
            option2 = findViewById(R.id.option2);
            option3 = findViewById(R.id.option3);
            option4 = findViewById(R.id.option4);
            timer_text = findViewById(R.id.timer);
            score = findViewById(R.id.score);
            correct_wrong_answer = findViewById(R.id.correct_wrong_answer);
            obSaveData.loadScore();
            obSaveData.showScore(level);
            switch (level) {
                case 1:
                    toolbar.setTitle("LEVEL 1");
                    break;
                case 2:
                    toolbar.setTitle("LEVEL 2");
                    break;
                case 3:
                    toolbar.setTitle("LEVEL 3");
                    break;
                case 4:
                    toolbar.setTitle("LEVEL 4");
                    break;
                case 5:
                    toolbar.setTitle("LEVEL 5");
                    break;
                case 6:
                    toolbar.setTitle("LEVEL 6");
                    break;
                case 7:
                    toolbar.setTitle("LEVEL 7");
                    break;
                case 8:
                    toolbar.setTitle("LEVEL 8");
                    break;
                case 9:
                    toolbar.setTitle("LEVEL 9");
                    break;
                case 10:
                    toolbar.setTitle("LEVEL 10");
            }
            setSupportActionBar(toolbar);
            start();

            option1.setOnClickListener(view -> showResult(Integer.parseInt(option1.getText().toString()), 1));
            option2.setOnClickListener(view -> showResult(Integer.parseInt(option2.getText().toString()), 2));
            option3.setOnClickListener(view -> showResult(Integer.parseInt(option3.getText().toString()), 3));
            option4.setOnClickListener(view -> showResult(Integer.parseInt(option4.getText().toString()), 4));
            nextButton.setOnClickListener(view -> start());
        } catch (Exception e) {
            Toast.makeText(DoFastMaths.this, "an error occurred in ocCreate()", Toast.LENGTH_SHORT).show();
        }
    }

    void showResult(int option_text, int option_number) {
        String correct = "CORRECT ANSWER";
        String wrong = "WRONG ANSWER";
        nextButton.setVisibility(View.VISIBLE);
        obTimer.stopTimer();
        correct_wrong_answer.setVisibility(View.VISIBLE);
        if (option_text == resultOfExpression) {
            correct_wrong_answer.setText(correct);
            correct_wrong_answer.setTextColor(Color.rgb(0, 0, 0));
            correct_wrong_answer.setBackgroundColor(Color.rgb(0, 255, 0));
            String update = Integer.toString(Integer.parseInt(score.getText().toString()) + obTimer.currentTime());
            score.setText(update);
            obSaveData.saveScore();
            obSaveData.loadScore();
        } else {
            correct_wrong_answer.setText(wrong);
            correct_wrong_answer.setTextColor(Color.rgb(255, 255, 255));
            correct_wrong_answer.setBackgroundColor(Color.rgb(255, 0, 0));
            switch (option_number) {
                case 1:
                    option1.setBackground((ContextCompat.getDrawable(this, R.drawable.wrong_answer_background)));
                    option1.setTextColor(Color.WHITE);
                    break;
                case 2:
                    option2.setBackground((ContextCompat.getDrawable(this, R.drawable.wrong_answer_background)));
                    option2.setTextColor(Color.WHITE);
                    break;
                case 3:
                    option3.setBackground((ContextCompat.getDrawable(this, R.drawable.wrong_answer_background)));
                    option3.setTextColor(Color.WHITE);
                    break;
                case 4:
                    option4.setBackground((ContextCompat.getDrawable(this, R.drawable.wrong_answer_background)));
                    option4.setTextColor(Color.WHITE);
            }
        }
        correct_answer_background();
    }

    void correct_answer_background() {
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);
        switch (obCalculator.answer_position) {
            case 0:
                option1.setBackground((ContextCompat.getDrawable(this, R.drawable.correct_answer_background)));
                option1.setTextColor(Color.BLACK);
                break;
            case 1:
                option2.setBackground((ContextCompat.getDrawable(this, R.drawable.correct_answer_background)));
                option2.setTextColor(Color.BLACK);
                break;
            case 2:
                option3.setBackground((ContextCompat.getDrawable(this, R.drawable.correct_answer_background)));
                option3.setTextColor(Color.BLACK);
                break;
            case 3:
                option4.setBackground((ContextCompat.getDrawable(this, R.drawable.correct_answer_background)));
                option4.setTextColor(Color.BLACK);
        }
    }


    void start() {
        try {
            program_expression = "";
            question.setText("0");
            resultOfExpression = 0;
            option1.setEnabled(true);
            option2.setEnabled(true);
            option3.setEnabled(true);
            option4.setEnabled(true);
            option1.setBackground((ContextCompat.getDrawable(this, R.drawable.button_custom)));
            option2.setBackground((ContextCompat.getDrawable(this, R.drawable.button_custom)));
            option3.setBackground((ContextCompat.getDrawable(this, R.drawable.button_custom)));
            option4.setBackground((ContextCompat.getDrawable(this, R.drawable.button_custom)));
            option1.setTextColor(Color.BLACK);
            option2.setTextColor(Color.BLACK);
            option3.setTextColor(Color.BLACK);
            option4.setTextColor(Color.BLACK);
            correct_wrong_answer.setVisibility(View.GONE);
            nextButton.setVisibility(View.GONE);
            timer_text.setVisibility(View.VISIBLE);
            obCalculator.create(level);
        } catch (Exception e) {
            Toast.makeText(DoFastMaths.this, "an error occurred in start()", Toast.LENGTH_SHORT).show();
        }
    }

}