package com.example.ritika.e_challan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;

public class AdminLoginActivity extends AppCompatActivity {

    EditText adminIdEdit;
    EditText adminPassEdit;

    Button adminSignInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f1f6e")));

        /*ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/

        adminIdEdit = (EditText) findViewById (R.id.editTextLoginAdminId);
        adminPassEdit = (EditText) findViewById (R.id.editTextLoginAdminPassword);
        adminSignInButton = (Button) findViewById (R.id.buttonAdminSignIn);
        StrictMode.ThreadPolicy sp= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sp);
    }


    public void adminLogin(View v)
    {

        ProgressDialog progress = new ProgressDialog(AdminLoginActivity.this);
        progress.setTitle("Sign In !!");
        progress.setMessage("Please Wait !!");
        progress.setCancelable(true);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();


        String adminId = URLEncoder.encode(adminIdEdit.getText().toString());
        String password = adminPassEdit.getText().toString();
        if (adminId.isEmpty()) {
            progress.dismiss();
            Toast.makeText(this, "Please enter admin id", Toast.LENGTH_LONG).show();
        } else if (password.isEmpty()) {
            progress.dismiss();
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();

        } else {


            try {
                HttpClient hc = new DefaultHttpClient();
                HttpPost hp = new HttpPost("http://studentportal.website/echallan/admin_login.php?adminId=" + adminId + "&password=" + password);
                Log.v("error", "http://studentportal.website/echallan/admin_login.php?adminId=" + adminId + "&password=" + password);
                HttpResponse hr = hc.execute(hp);
                String response = EntityUtils.toString(hr.getEntity()).trim();
                System.out.print(response);

                if (response.equals("ok")) {
                    // System.out.print("hello");
                    Intent i = new Intent(this, AdminPortalActivity.class);
                    startActivity(i);
                    finish();
                    SharedPreferences sp = getSharedPreferences("mydata", Context.MODE_PRIVATE);
                    SharedPreferences.Editor e = sp.edit();
                    e.putString("adminId", adminId);
                    e.putString("password", password);
                    e.commit();

                } else {
                    progress.dismiss();
                    Toast.makeText(AdminLoginActivity.this, "Wrong Admin Id and Password", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Log.v("error :", e.toString());
            }
        }
    }
}
