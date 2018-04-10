package com.example.ritika.e_challan;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
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
import org.json.JSONObject;

public class UpdatePoliceActivity extends AppCompatActivity {

    String pId,name,pass,pNo;
    EditText policeIdEdit, passEdit,phoneEdit,nameEdit;

    Button buttonUpdatePoliceDet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_police);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f1f6e")));
        //Toast.makeText(this,"before function",Toast.LENGTH_LONG).show();

        buttonUpdatePoliceDet = (Button) findViewById(R.id.buttonUpdatePoliceDetails);

        Intent i = getIntent();
        pId = i.getStringExtra("policeId");
        name= i.getStringExtra("name");
        pass= i.getStringExtra("password");
        pNo= i.getStringExtra("phoneNo");

        policeIdEdit = (EditText) findViewById(R.id.editTextUpdatePoliceId);
        passEdit = (EditText) findViewById(R.id.editTextUpdatePolicePassword);
        phoneEdit = (EditText) findViewById(R.id.editTextUpdatePolicePhoneNo);
        nameEdit = (EditText) findViewById(R.id.editTextUpdatePoliceName);

        policeIdEdit.setText(pId);
        phoneEdit.setText(pNo);
        passEdit.setText(pass);
        nameEdit.setText(name);
        StrictMode.ThreadPolicy sp= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sp);

        buttonUpdatePoliceDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

    }
    public void update()
    {
        String policeId=policeIdEdit.getText().toString();
        String pNo=phoneEdit.getText().toString();
        String pass=passEdit.getText().toString();
        String name=nameEdit.getText().toString();

        try
        {
            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/update_police_details.php?pId="+pId+"&pass="+pass+"&pNo="+pNo+"&name="+name+"&policeId="+policeId);
            //  Log.v("error","http://studentportal.website/echallan/add_police_details.php?policeId="+policeId+"&password="+password+"&phoneNo"+phoneNo+"&name="+name);
            HttpResponse hr = hc.execute(hp);
            String response= EntityUtils.toString(hr.getEntity()).trim();
            //System.out.print(response);

            if(response.equals("ok"))
            {
                // System.out.print("hello");
                Toast.makeText(this,"Successfully updated !",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this,"Try Again",Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.v("error :",e.toString());
        }





    }

}
