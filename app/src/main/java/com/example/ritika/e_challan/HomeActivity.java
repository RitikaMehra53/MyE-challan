package com.example.ritika.e_challan;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f1f6e")));

    }

    public void PolicePortal(View view) {
        Intent intent = new Intent(HomeActivity.this, PoliceLoginActivity.class);
        startActivity(intent);
    }

    public void CommonUserPortal(View view) {
        Intent intent = new Intent(HomeActivity.this, CommonUserPortalActivity.class);
        startActivity(intent);

    }

    public void AdminPortal(View view) {
        Intent intent = new Intent(HomeActivity.this, AdminLoginActivity.class);
        startActivity(intent);

    }
}
