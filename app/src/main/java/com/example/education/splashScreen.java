package com.example.education;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splashScreen extends AppCompatActivity {

    long SPLASH = 2100L;

    Animation splash;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        splash = AnimationUtils.loadAnimation(this, R.anim.animation);
        logo = findViewById(R.id.learn_logo);

        logo.setAnimation(splash);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(splashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH);
    }
}