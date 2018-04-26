package com.example.ritika.e_challan;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class HistoryActivity extends AppCompatActivity {

    ListView listView2;
    String rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable
                (Color.parseColor("#1f1f6e")));

        listView2 = (ListView)findViewById
                (R.id.listViewTrialHistory);
        Intent i = getIntent();
        rc = i.getStringExtra("rc");

        get_history_challans();
    }

    public void get_history_challans(){

        //Pattern pattern = new Pattern([a-zA-Z0-9]*);

        if (rc.isEmpty() || rc.matches("[A-Za-z0-9]{10}")) {
            Toast.makeText(this, "Enter valid RC number", Toast.LENGTH_LONG).show();
        }


        try{


            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost
                    ("http://studentportal.website/echallan/hist.php?rcNo="+rc);
            //    Log.v("error","http://studentportal.website/echallan/reg.php?challanNo="+challanNo+"&name="+name+"&phoneNo="+phoneNo+"&rcNo="+rcNo+"&licenceNo="+licenceNo+"&date="+date+"&time="+time+"&description="+description+"&place="+place);


            // Toast.makeText(getActivity(),"Hello try",Toast.LENGTH_LONG).show();

            HttpResponse hr = hc.execute(hp);
            String result= EntityUtils.toString(hr.getEntity
                    ()).trim();

            //Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();


            JSONArray js= new JSONArray(result);
            Log.d("CHALLAN",js.toString()+"\n");

            if(js.length()==0){
                Toast.makeText(this,"No data found",Toast.LENGTH_LONG).show();
            }



            else{
                AllData allData=new AllData();
                for (int i=0;i<js.length();i++){
                    JSONObject a=new JSONObject();
                    a=js.getJSONObject(i);
                    String name=a.getString("name");
                    // Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                    String challanNo=a.getString("challanNo");
                    String date1=a.getString("date1");
                    String place=a.getString("place");

                    String time1=a.getString("time1");
                    // Toast.makeText(getActivity(),challanNo+time1+date1+place,Toast.LENGTH_LONG).show();

                    String licenceNo=a.getString("licenceNo");
                    String rcN=a.getString("rcN");
                    String description=a.getString("description");
                    String fineAmount=a.getString("fineAmount");

                    String payable=a.getString("payable");
                    String phoneNo=a.getString("phoneNo");
                    //Toast.makeText(getActivity(),licenceNo+rcN+description+fineAmount,Toast.LENGTH_LONG).show();
                    //  Toast.makeText(getActivity(),payable+phoneNo,Toast.LENGTH_LONG).show();

                    Data data1 = new Data();

                    data1.challanNo=challanNo;
                    data1.name=name;
                    data1.date1=date1;
                    data1.description=description;
                    data1.fineAmount=fineAmount;
                    data1.licenceNo=licenceNo;
                    data1.payable=payable;
                    data1.phoneNo=phoneNo;
                    data1.time1=time1;
                    data1.place=place;
                    data1.rcNo=rcN;

                    allData.details.add(data1);





                }

                HistoryBaseAdapter historyBaseAdapter=new
                        HistoryBaseAdapter(this,allData.details);
                listView2.setAdapter(historyBaseAdapter);
            }



        }
        catch(Exception e)
        {
            Toast.makeText(this,"Network Problem!!",Toast.LENGTH_LONG).show
                    ();
            //Log.v("error :",e.toString());
        }
    }
}
