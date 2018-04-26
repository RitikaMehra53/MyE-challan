package com.example.ritika.e_challan;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

public class UpdatePoliceFragment extends Fragment {

    String pId,name,pass,pNo;
    EditText policeIdEdit;

    Button updateButton;


    public UpdatePoliceFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_update_police, container, false);


    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




        Button b = (Button) getActivity().findViewById(R.id.buttonUpdatePoliceDetails);

        b.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Hello try",Toast.LENGTH_LONG).show();

                policeIdEdit = (EditText) getActivity().findViewById(R.id.editTextUpdatePoliceId);
                StrictMode.ThreadPolicy sp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(sp);

                Update();
            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Update()
    {

        String policeId = URLEncoder.encode(policeIdEdit.getText().toString());
        if (policeId.isEmpty() ) {
            Toast.makeText(getActivity(), "Enter police id", Toast.LENGTH_LONG).show();
        }

        else if(!policeId.matches("[a-zA-Z0-9]*"))
        {
            Toast.makeText(getActivity(), "Enter valid Police Id", Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(getActivity(),policeId,Toast.LENGTH_LONG).show();
        try{
            //Toast.makeText(getActivity(),"after function",Toast.LENGTH_LONG).show();

           /* Intent i = getIntent();
            String policeId = i.getStringExtra("policeId");
            Toast.makeText(this,policeId,Toast.LENGTH_LONG).show();
*/
            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/get_police_details.php?policeId="+policeId);
            //    Log.v("error","http://studentportal.website/echallan/reg.php?challanNo="+challanNo+"&name="+name+"&phoneNo="+phoneNo+"&rcNo="+rcNo+"&licenceNo="+licenceNo+"&date="+date+"&time="+time+"&description="+description+"&place="+place);
            HttpResponse hr = hc.execute(hp);
            String result= EntityUtils.toString(hr.getEntity()).trim();
            //Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();

            JSONArray js = new JSONArray(result);
            Log.d("CHALLAN", js.toString() + "\n");


            // AllData allData = new AllData();
            for (int i = 0; i < js.length(); i++) {
                JSONObject a = new JSONObject();
                a = js.getJSONObject(i);
                pId= a.getString("policeId");
                // Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                name= a.getString("name");
                pass= a.getString("password");
                pNo = a.getString("phoneNo");
                //Toast.makeText(getActivity(),"Get Details ---"+name +pNo+name+pass,Toast.LENGTH_LONG).show();
            }

            Intent i= new Intent(getActivity(),UpdatePoliceActivity.class);
            i.putExtra("policeId",pId);
            i.putExtra("name",name);
            i.putExtra("password",pass);
            i.putExtra("phoneNo",pNo);
            startActivity(i);


            if(result.equals("ok"))
            {
                Toast.makeText(getActivity(),"Succesfully Updated",Toast.LENGTH_LONG).show();


            }
            else
            {
                //progress.dismiss();
                Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
            }

        }
        catch(Exception e)
        {
            Toast.makeText(getActivity(),"Catchhh",Toast.LENGTH_LONG).show();
            //Log.v("error :",e.toString());
        }


    }



}
