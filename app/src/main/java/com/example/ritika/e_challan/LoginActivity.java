package com.example.ritika.e_challan;

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

public class LoginActivity extends AppCompatActivity {

     EditText emailEdit,passEdit;

    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f1f6e")));

        /*ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/

        passEdit = (EditText) findViewById (R.id.editTextLoginPassword);
        signInButton = (Button) findViewById (R.id.buttonSignIn);
        StrictMode.ThreadPolicy sp= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sp);

        /*signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,PolicePortalActivity.class);
                startActivity(intent);
            }
        });*/
    }


    public void login(View v)
    {

        String email= URLEncoder.encode(emailEdit.getText().toString());
        String password=passEdit.getText().toString();


        try
        {
            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/signin.php?email="+email+"&password="+password);
            Log.v("error","http://studentportal.website/echallan/signin.php?email="+email+"&password="+password);
            HttpResponse hr = hc.execute(hp);
            String array= EntityUtils.toString(hr.getEntity()).trim();
            if(array.equals("ok"))
            {
                Intent i= new Intent(this,PolicePortalActivity.class);
                startActivity(i);
                SharedPreferences sp = getSharedPreferences("mydata", Context.MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();

                e.putString("email", email);
                e.putString("password", password);



                e.commit();

            }
            else
            {
                Toast.makeText(LoginActivity.this,"Wrong Username and Password",Toast.LENGTH_LONG).show();
            }
            /**  String full_name = js.getString("fullname");
             String address = js.getString("address");
             String age = js.getString("age");
             String hb = js.getString("hb");
             String weight = js.getString("weight");
             String blood_group = js.getString("blood_group");
             String contact_no = js.getString("contact_no");**/


            /** SharedPreferences sp = getSharedPreferences("mydata", Context.MODE_PRIVATE);
             SharedPreferences.Editor e = sp.edit();

             e.putString("username", username);
             e.putString("password", password);
             e.putString("full_name",full_name);
             e.putString("hb",hb);
             e.putString("contact_no",contact_no);
             e.putString("address", address);
             e.putString("age",age);
             e.putString("blood_group", blood_group);
             e.putString("weight", weight);



             e.commit();**/

        }
        catch(Exception e)
        {
            Log.v("error :",e.toString());
        }

    }




}

