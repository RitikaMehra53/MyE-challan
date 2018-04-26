package com.example.ritika.e_challan;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class DescriptionActivity extends AppCompatActivity {

    ListView listView3;
    String descrription,fine1,fine2,fine3,des_1,fine_1,fine_2,fine_3;
    TextView desEdit;
    EditText fine1Edit,fine2Edit,fine3Edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f1f6e")));

        //listView3 = (ListView)findViewById(R.id.listViewTrialDescription);

        desEdit =(TextView)findViewById(R.id.description);
        fine1Edit =(EditText)findViewById(R.id.fine1);
        fine2Edit =(EditText)findViewById(R.id.fine2);
        fine3Edit =(EditText)findViewById(R.id.fine3);

        Intent i = getIntent();
        descrription = i.getStringExtra("description");
        fine1= i.getStringExtra("fine1");
        fine2= i.getStringExtra("fine2");
        fine3= i.getStringExtra("fine3");

        desEdit.setText(descrription);
        fine1Edit.setText(fine1);
        fine2Edit.setText(fine2);
        fine3Edit.setText(fine3);
        StrictMode.ThreadPolicy sp= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sp);

    }


    public void update_challan_amount(View view) {
        //Toast.makeText(this,"Successfully Updated",Toast.LENGTH_LONG).show();

        des_1=desEdit.getText().toString();
        fine_1=fine1Edit.getText().toString();
        fine_2=fine2Edit.getText().toString();
        fine_3=fine3Edit.getText().toString();
        //Toast.makeText(this,"before try",Toast.LENGTH_LONG).show();

        try
        {
            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/update_challan.php?des1="+des_1+"&fine1="+fine_1+"&fine2="+fine_2+"&fine3="+fine_3);

            //  Log.v("error","http://studentportal.website/echallan/add_police_details.php?policeId="+policeId+"&password="+password+"&phoneNo"+phoneNo+"&name="+name);
            HttpResponse hr = hc.execute(hp);
            String response= EntityUtils.toString(hr.getEntity()).trim();
            //System.out.print(response);
            //Toast.makeText(this,response,Toast.LENGTH_LONG).show();
            //Toast.makeText(this,"old id .."+pId+"new id .."+policeId+"phone no.."+pNo+"pass .."+pass+"name .."+name,Toast.LENGTH_LONG).show();

            if(response.equals("ok"))
            {
                // System.out.print("hello");
                Toast.makeText(this,"Successfully updated !",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this,response,Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.v("error :",e.toString());
        }
    }

}
