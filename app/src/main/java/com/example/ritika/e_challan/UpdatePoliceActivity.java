package com.example.ritika.e_challan;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class UpdatePoliceActivity extends AppCompatActivity {

    String pId,name,pass,pNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_police);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f1f6e")));


        try{

            Intent i = getIntent();
            String policeId = i.getStringExtra("policeId");

            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/update_police_details.php?policeId="+policeId);
            //    Log.v("error","http://studentportal.website/echallan/reg.php?challanNo="+challanNo+"&name="+name+"&phoneNo="+phoneNo+"&rcNo="+rcNo+"&licenceNo="+licenceNo+"&date="+date+"&time="+time+"&description="+description+"&place="+place);
            HttpResponse hr = hc.execute(hp);
            String result= EntityUtils.toString(hr.getEntity()).trim();
            Toast.makeText(this,result,Toast.LENGTH_LONG).show();

            JSONObject js= new JSONObject(result);
            pId = js.getString("policeId");

            name= js.getString("name");
            pNo= js.getString("phoneNo");
            pass= js.getString("password");
            Toast.makeText(this,"Get Details ---"+name +pNo+name+pass,Toast.LENGTH_LONG).show();


           /* e13.setText(full_name);
            e14.setText(email);

            e16.setText(contact_no);
            e17.setText(age);
            e19.setText(weight);
            e20.setText(address);
            e21.setText(blood_group);
            e22.setText(hb);*/




            if(result.equals("ok"))
            {
                Toast.makeText(this,"Succesfully deleted",Toast.LENGTH_LONG).show();


            }
            else
            {
                //progress.dismiss();
                Toast.makeText(this,result,Toast.LENGTH_LONG).show();
            }

        }
        catch(Exception e)
        {
            Toast.makeText(this,"Catchhh",Toast.LENGTH_LONG).show();
            //Log.v("error :",e.toString());
        }
    }
}
