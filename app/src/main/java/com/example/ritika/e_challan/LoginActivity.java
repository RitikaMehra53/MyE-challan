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

public class LoginActivity extends AppCompatActivity {

    EditText policeIdEdit;
    EditText passEdit;

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
        policeIdEdit = (EditText) findViewById (R.id.editTextLoginPoliceId);
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

        ProgressDialog progress = new ProgressDialog(LoginActivity.this);
        progress.setTitle("Sign In !!");
        progress.setMessage("Please Wait !!");
        progress.setCancelable(true);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        /*Intent i= new Intent(this,PolicePortalActivity.class);
        startActivity(i);*/

        String policeId= URLEncoder.encode(policeIdEdit.getText().toString());
        String password=passEdit.getText().toString();
        Intent i= new Intent(this,PolicePortalActivity.class);
        startActivity(i);
        finish();

        /*try
        {




            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/ab.php?policeId="+policeId+"&password="+password);
            Log.v("error","http://studentportal.website/echallan/ab.php?policeId="+policeId+"&password="+password);
            HttpResponse hr = hc.execute(hp);
            String response= EntityUtils.toString(hr.getEntity()).trim();
            System.out.print(response);


            if(response.equals("ok"))
            {
                // System.out.print("hello");
                Intent i= new Intent(this,PolicePortalActivity.class);
                startActivity(i);
                finish();
                *//*SharedPreferences sp = getSharedPreferences("mydata", Context.MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();
                e.putString("policeId", policeId);
                e.putString("password", password);
                e.commit();*//*


                if (response.equals("ok")) {
                    // System.out.print("hello");
                    Intent i = new Intent(this, PolicePortalActivity.class);
                    startActivity(i);
                    finish();
                    SharedPreferences sp = getSharedPreferences("mydata", Context.MODE_PRIVATE);
                    SharedPreferences.Editor e = sp.edit();
                    e.putString("policeId", policeId);
                    e.putString("password", password);
                    e.commit();

                } else {
                    if (!policeId.matches("[a-zA-Z0-9]+")) {
                        progress.dismiss();
                        Toast.makeText(this, "Invalid police id", Toast.LENGTH_LONG).show();

                    }
                    else {
                        progress.dismiss();
                        Toast.makeText(PoliceLoginActivity.this, "Either Wrong Police Id or Password", Toast.LENGTH_LONG).show();
                    }
                }

            } catch (Exception e) {
                Log.v("error :", e.toString());
            }
            else
            {
                progress.dismiss();
                Toast.makeText(LoginActivity.this,"Wrong Username and Password",Toast.LENGTH_LONG).show();
            }

        }
        catch(Exception e)
        {
            Log.v("error :",e.toString());
        }*/
    }
}

