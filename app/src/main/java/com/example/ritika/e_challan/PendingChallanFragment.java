package com.example.ritika.e_challan;

import android.content.Intent;
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
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.regex.Pattern;

public class PendingChallanFragment extends Fragment {
    String rc;
    //String phoneNo,name,rcN,licenceNo,place,description,challanNo,payable,fineAmount,date1,time1;

    EditText rcNo;
    Button searchPendingButton;
    ListView  listView1;

    public PendingChallanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_pending_challan, container, false);

    }

     public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         searchPendingButton=(Button) getActivity().findViewById(R.id.buttonSearchPendingChallan);
         searchPendingButton.setOnClickListener(new View.OnClickListener(){
             @RequiresApi(api = Build.VERSION_CODES.N)
             @Override
             public void onClick(View v) {

                 rcNo =(EditText)getActivity().findViewById(R.id.editTextPendingChallanRCNo);
                 rc= URLEncoder.encode(rcNo.getText().toString());
                 Intent i= new Intent(getActivity(),PendingActivity.class);
                 i.putExtra("rc",rc);
                 startActivity(i);

             }
         });


    }
}
