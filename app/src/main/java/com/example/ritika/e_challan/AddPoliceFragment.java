package com.example.ritika.e_challan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;
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
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;

public class AddPoliceFragment extends Fragment {

    EditText policeIdEdit;
    EditText passwordEdit;
    EditText phoneNoEdit;
    EditText nameEdit;

    Button addButton;


    public AddPoliceFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_add_police, container, false);

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        addButton = (Button) getActivity().findViewById(R.id.buttonAddPolice);

        addButton.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                policeIdEdit = (EditText) getActivity().findViewById(R.id.editTextAddPoliceId);
                passwordEdit = (EditText) getActivity().findViewById(R.id.editTextAddPolicePassword);
                phoneNoEdit = (EditText) getActivity().findViewById(R.id.editTextAddPolicePhoneNo);
                nameEdit = (EditText) getActivity().findViewById(R.id.editTextAddPoliceName);

                add();

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void add()
    {
        StrictMode.ThreadPolicy sp= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sp);


        String a= URLEncoder.encode(policeIdEdit.getText().toString());
        String b= URLEncoder.encode(passwordEdit.getText().toString());
        String c= URLEncoder.encode(phoneNoEdit.getText().toString());

        String d= URLEncoder.encode(nameEdit.getText().toString());



        try
        {
            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/add_police_details.php?policeId="+a+"&password="+b+"&phoneNo="+c+"&name="+d);
          //  Log.v("error","http://studentportal.website/echallan/add_police_details.php?policeId="+policeId+"&password="+password+"&phoneNo"+phoneNo+"&name="+name);
            HttpResponse hr = hc.execute(hp);
            String response= EntityUtils.toString(hr.getEntity()).trim();
            //System.out.print(response);

            if(response.equals("ok"))
            {
                // System.out.print("hello");
                Toast.makeText(getActivity(),"Successfully added !",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.v("error :",e.toString());
        }
    }


}
