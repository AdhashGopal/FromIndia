package com.app.fromindia.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fromindia.R;
import com.app.fromindia.helper.CommonValues;

public class FIHomePageActivity extends AppCompatActivity implements CommonValues {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // GotoMainActivity();
            }
        }, SPLASH_TIME);
    }
}
