package com.example.education;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("StaticFieldLeak")
    private static MainActivity obMainActivity;

    Toolbar toolbar;

    LinearLayout level1, level2, level3, level4, level5,
            level6, level7, level8, level9, level10;

    int[] min_score = {-1, 100, 80, 120, 80, 100, 100, 150, 90, 150};
//    int[] min_score = {-1, 14, 1, 120, 80, 100, 100, 150, 90, 150};

    SavingData obSaveData;
    Intent intent;
    AlertMessage alertMessage = new AlertMessage();

    int[] saved_score = new int[10];
//    int[] saved_score = {20, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    @SuppressLint({"StaticFieldLeak", "SetTextI18n"})

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle("LEVELS");
            setSupportActionBar(toolbar);
            obMainActivity = this;
            obSaveData = new SavingData();
            level1 = findViewById(R.id.level1);
            level2 = findViewById(R.id.level2);
            level3 = findViewById(R.id.level3);
            level4 = findViewById(R.id.level4);
            level5 = findViewById(R.id.level5);
            level6 = findViewById(R.id.level6);
            level7 = findViewById(R.id.level7);
            level8 = findViewById(R.id.level8);
            level9 = findViewById(R.id.level9);
            level10 = findViewById(R.id.level10);
            obSaveData.loadScore();
            intent = new Intent(this, DoFastMaths.class);

            level1.setOnClickListener(view -> goToQuestion(1, min_score[0]));
            level2.setOnClickListener(view -> goToQuestion(2, min_score[1]));
            level3.setOnClickListener(view -> goToQuestion(3, min_score[2]));
            level4.setOnClickListener(view -> goToQuestion(4, min_score[3]));
            level5.setOnClickListener(view -> goToQuestion(5, min_score[4]));
            level6.setOnClickListener(view -> goToQuestion(6, min_score[5]));
            level7.setOnClickListener(view -> goToQuestion(7, min_score[6]));
            level8.setOnClickListener(view -> goToQuestion(8, min_score[7]));
            level9.setOnClickListener(view -> goToQuestion(9, min_score[8]));
            level10.setOnClickListener(view -> goToQuestion(10, min_score[9]));
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "an error occurred", Toast.LENGTH_SHORT).show();
        }
    }

    void goToQuestion(int i, int minScore) {
        if (minScore != -1 && saved_score[i - 2] < minScore)
            alertMessage.show(getSupportFragmentManager(), "alert");
        else {
            intent.putExtra("LEVEL", i);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.info:
                intent = new Intent(this, Information.class);
                startActivity(intent);
                return true;
            case R.id.allScores:
                intent = new Intent(this, Scores.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public static MainActivity getInstance() {
        return obMainActivity;
    }
}