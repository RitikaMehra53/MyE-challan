package com.example.ritika.e_challan;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ProgressBar mProgress;
    TextView textView;
    private int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f1f6e")));

        getSupportActionBar().hide();

        mProgress = (ProgressBar) findViewById(R.id.progressBarSplash);
        textView = (TextView) findViewById(R.id.textViewSplashProgress);
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (value == 100) {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    textView.setText("Opening: " + value + "%");
                    mProgress.setProgress(value);
                }
                value+= 10;
                handler.postDelayed(this,500);
            }
        };
        handler.postDelayed(runnable, 100);
    }
}
