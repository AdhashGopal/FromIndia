package com.app.fromindia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fromindia.R;
import com.app.fromindia.helper.CommonValues;

public class FISplashActivity extends AppCompatActivity implements CommonValues {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                GotoMainActivity();
            }
        }, SPLASH_TIME);
    }

    private void GotoMainActivity() {
        Intent intent = new Intent(FISplashActivity.this, FILoginActivity.class);
        startActivity(intent);
        finish();
    }
}
