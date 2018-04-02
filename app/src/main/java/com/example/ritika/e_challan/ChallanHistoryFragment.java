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

    ListView detailList;
    EditText rcNo;
    Button searchButton;
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


                search();

            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void search(){

        rc= URLEncoder.encode(rcNo.getText().toString());

        /*ChallanDetails challanDetails=new ChallanDetails();
        challanDetails.setChallanNo(123);
        challanDetails.setName("Ajay");


        final List<ChallanDetails> details = new ArrayList<>();
        detailList = (ListView) getActivity().findViewById(R.id.listViewChallanHistorylist);

        HistoryBaseAdapter historyBaseAdapter = new HistoryBaseAdapter(getActivity(), details);
        detailList.setAdapter(historyBaseAdapter);*/

        try{


            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/history.php?rcNo="+rc);
            //    Log.v("error","http://studentportal.website/echallan/reg.php?challanNo="+challanNo+"&name="+name+"&phoneNo="+phoneNo+"&rcNo="+rcNo+"&licenceNo="+licenceNo+"&date="+date+"&time="+time+"&description="+description+"&place="+place);


            Toast.makeText(getActivity(),"Hello try",Toast.LENGTH_LONG).show();

            HttpResponse hr = hc.execute(hp);
            String result= EntityUtils.toString(hr.getEntity()).trim();

            Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();

            if(result.equals("ok"))
            {
                Toast.makeText(getActivity(),"Succesfully Registered",Toast.LENGTH_LONG).show();
                //Intent i= new Intent(getActivity(),PolicePortalActivity.class);
                //startActivity(i);

            }
            else
            {
                //progress.dismiss();
                Toast.makeText(getActivity(),"Try Again!",Toast.LENGTH_LONG).show();
            }

        }
        catch(Exception e)
        {
            Toast.makeText(getActivity(),"Catchhh",Toast.LENGTH_LONG).show();
            //Log.v("error :",e.toString());
        }
    }

}


