package com.example.education;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class Timer {

    DoFastMaths ob = DoFastMaths.getInstance();

    void startTimer() {
        try {
            ob.timer = new CountDownTimer(ob.timeInMillis, 1000) {
                @Override
                public void onTick(long l) {
                    ob.timeInMillis = l;
                    String timeInSeconds = Integer.toString((int) ob.timeInMillis / 1000);
                    ob.timer_text.setText(timeInSeconds);
                }

                @Override
                public void onFinish() {
                    ob.option1.setBackground((ContextCompat.getDrawable(ob, R.drawable.time_up_background)));
                    ob.option2.setBackground((ContextCompat.getDrawable(ob, R.drawable.time_up_background)));
                    ob.option3.setBackground((ContextCompat.getDrawable(ob, R.drawable.time_up_background)));
                    ob.option4.setBackground((ContextCompat.getDrawable(ob, R.drawable.time_up_background)));
                    ob.correct_answer_background();
                    ob.correct_wrong_answer.setVisibility(View.VISIBLE);
                    String timeUp = "TIME UP!";
                    ob.correct_wrong_answer.setText(timeUp);
                    ob.correct_wrong_answer.setTextColor(Color.BLACK);
                    ob.correct_wrong_answer.setBackgroundColor(Color.YELLOW);
                    ob.nextButton.setVisibility(View.VISIBLE);
                }
            }.start();

        } catch (Exception e) {
            Toast.makeText(ob, "an error occurred in startTimer()", Toast.LENGTH_SHORT).show();
        }
    }

    public void setStartTime(int timeInSeconds) {
        try {
            ob.startTime = timeInSeconds;
            ob.timeInMillis = timeInSeconds * 1000L;
            startTimer();

        } catch (Exception e) {
            Toast.makeText(ob, "an error occurred in setStartTime()", Toast.LENGTH_SHORT).show();
        }
    }


    void stopTimer() {
        try {
            ob.timer.cancel();
            ob.timer_text.setVisibility(View.GONE);

        } catch (Exception e) {
            Toast.makeText(ob, "an error occurred in stopTimer()", Toast.LENGTH_SHORT).show();
        }
    }


    int currentTime() {
        try {
            return (int) ob.timeInMillis / 1000;

        } catch (Exception e) {
            Toast.makeText(null, "an error occurred in currentTime()", Toast.LENGTH_SHORT).show();
            return -1;
        }
    }

}
