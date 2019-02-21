package com.example.klightlib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.intro.IntroTutorial;

public class MainActivityApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        startActivity(new Intent(this, IntroTutorial.class));
    }
}
