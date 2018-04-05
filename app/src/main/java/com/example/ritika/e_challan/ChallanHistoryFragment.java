package com.example.ritika.e_challan;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.R.attr.description;

public class ChallanHistoryFragment extends Fragment {
String rc;
    //String phoneNo,name,rcN,licenceNo,place,description,challanNo,payable,fineAmount,date1,time1;

    EditText rcNo;
    Button searchButton;
    ListView listView;
    public ChallanHistoryFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_challan_history, container, false);

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        searchButton=(Button) getActivity().findViewById(R.id.buttonSearchChallanHistory);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                rcNo =(EditText)getActivity().findViewById(R.id.editTextChallanHistoryRCNo);
                listView = (ListView) getActivity().findViewById(R.id.listViewChallanHistorylist);

                search();

            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void search(){

        rc= URLEncoder.encode(rcNo.getText().toString());

        if (rc.isEmpty() || rc.matches("[A-Za-z0-9]{10}")) {
            Toast.makeText(getActivity(), "Invalid police id", Toast.LENGTH_LONG).show();
        }


        try{


            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/hist.php?rcNo="+rc);
            //    Log.v("error","http://studentportal.website/echallan/reg.php?challanNo="+challanNo+"&name="+name+"&phoneNo="+phoneNo+"&rcNo="+rcNo+"&licenceNo="+licenceNo+"&date="+date+"&time="+time+"&description="+description+"&place="+place);


            Toast.makeText(getActivity(),"Hello try",Toast.LENGTH_LONG).show();

            HttpResponse hr = hc.execute(hp);
            String result= EntityUtils.toString(hr.getEntity()).trim();

            Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();

            AllData allData=new AllData();
            JSONArray js= new JSONArray(result);
            Log.d("CHALLAN",js.toString()+"\n");
            for (int i=0;i<js.length();i++){
                JSONObject a=new JSONObject();
                a=js.getJSONObject(i);
                String name=a.getString("name");
                String challanNo=a.getString("challanNo");
                String time1=a.getString("time");
                String date1=a.getString("date1");
                String place=a.getString("place");
                String licenceNo=a.getString("licenceNo");
                String rcN=a.getString("rcN");
                String description=a.getString("description");
                String fineAmount=a.getString("fineAmount");
                String payable=a.getString("payable");
                String phoneNo=a.getString("phoneNo");
Toast.makeText(getActivity(),name+challanNo+time1+date1+place+licenceNo+rcN+description+fineAmount+payable+phoneNo,Toast.LENGTH_LONG);
                Data data = new Data();

                data.challanNo=challanNo;
                data.name=name;
                data.date1=date1;
                data.description=description;
                data.fineAmount=fineAmount;
                data.licenceNo=licenceNo;
                data.payable=payable;
                data.phoneNo=phoneNo;
                data.time1=time1;
                data.place=place;
                data.rcNo=rcN;

                allData.details.add(data);

               Toast.makeText(getActivity(),"Details "+data.name+data.rcNo,Toast.LENGTH_LONG).show();
            }

            /*challanNo="101";
            name="ABC";;
            date1="2/3/18";
            description="Helmet";
            fineAmount="300";
            licenceNo="lic123";
            payable="No";
            phoneNo="1234456666";
            time1="12:45:50";
            place="Ambala";
            rcN="RC123";*/

            HistoryBaseAdapter historyBaseAdapter=new HistoryBaseAdapter(getActivity(),allData.details);
            listView.setAdapter(historyBaseAdapter);




                /*e13.setText(full_name);
                e14.setText(email);

                e16.setText(contact_no);
                e17.setText(age);
                e19.setText(weight);
                e20.setText(address);
                e21.setText(blood_group);
                e22.setText(hb);*/



        }
        catch(Exception e)
        {
            Toast.makeText(getActivity(),"Catchhh",Toast.LENGTH_LONG).show();
            //Log.v("error :",e.toString());
        }
    }

}


