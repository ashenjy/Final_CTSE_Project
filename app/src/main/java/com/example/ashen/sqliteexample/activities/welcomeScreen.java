package com.example.ashen.sqliteexample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.ashen.sqliteexample.R;

public class welcomeScreen extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        ActionBar myActionBar = getSupportActionBar();
        if (myActionBar != null) {
            myActionBar.hide();
        }

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(welcomeScreen.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}