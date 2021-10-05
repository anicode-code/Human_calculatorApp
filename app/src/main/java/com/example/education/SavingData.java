package com.example.education;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.widget.Toast;

public class SavingData {

    public static final String SHARED_PREFERENCES = "shared_preferences";
    public static final String[] SCORE_SAVE = {"score_save1", "score_save2", "score_save3", "score_save4", "score_save5",
            "score_save6", "score_save7", "score_save8", "score_save9", "score_save10"};
    DoFastMaths obDoFastMaths = DoFastMaths.getInstance();
    MainActivity obMainActivity = MainActivity.getInstance();

    public void saveScore() {
        try {
            SharedPreferences sharedPreferences = obDoFastMaths.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(SCORE_SAVE[obDoFastMaths.level - 1], Integer.parseInt(obDoFastMaths.score.getText().toString()));
            editor.apply();

        } catch (Exception e) {
            Toast.makeText(obDoFastMaths, "an error occurred in saveScore()", Toast.LENGTH_SHORT).show();
        }
    }


    public void loadScore() {
        try {
            SharedPreferences sharedPreferences = obMainActivity.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
            for (int i = 0; i < 10; i++)
                obMainActivity.saved_score[i] = sharedPreferences.getInt(SCORE_SAVE[i], 0);
        } catch (Exception e) {
            Toast.makeText(obMainActivity, "an error occurred in loadScore()", Toast.LENGTH_SHORT).show();
        }
    }

    public void showScore(int level_number) {
        try {
            String update = Integer.toString(obMainActivity.saved_score[level_number - 1]);
            obDoFastMaths.score.setText(update);
        } catch (Exception e) {
            Toast.makeText(obDoFastMaths, "an error occurred in showScore()", Toast.LENGTH_SHORT).show();
        }
    }

}
