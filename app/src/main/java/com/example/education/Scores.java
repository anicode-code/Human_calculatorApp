package com.example.education;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class Scores extends AppCompatActivity {

    TextView[] allScores = new TextView[10];
    TextView Total_sum;
    MainActivity obMainActivity = MainActivity.getInstance();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("SCORES");
        setSupportActionBar(toolbar);
        allScores[0] = findViewById(R.id.level1_Score);
        allScores[1] = findViewById(R.id.level2_Score);
        allScores[2] = findViewById(R.id.level3_Score);
        allScores[3] = findViewById(R.id.level4_Score);
        allScores[4] = findViewById(R.id.level5_Score);
        allScores[5] = findViewById(R.id.level6_Score);
        allScores[6] = findViewById(R.id.level7_Score);
        allScores[7] = findViewById(R.id.level8_Score);
        allScores[8] = findViewById(R.id.level9_Score);
        allScores[9] = findViewById(R.id.level10_Score);
        Total_sum = findViewById(R.id.totalScore);
        show();
    }

    @SuppressLint("SetTextI18n")
    void show() {
        long sum = 0;
        for (int i = 0; i < obMainActivity.saved_score.length; i++) {
            sum += obMainActivity.saved_score[i];
            String s = Integer.toString(obMainActivity.saved_score[i]);
            if (obMainActivity.min_score[i] != -1 && obMainActivity.saved_score[i - 1] < obMainActivity.min_score[i])
                allScores[i].setText("NOT REACHED");
            else
                allScores[i].setText("Score: " + s);
        }
        Total_sum.setText("" + sum);
    }
}