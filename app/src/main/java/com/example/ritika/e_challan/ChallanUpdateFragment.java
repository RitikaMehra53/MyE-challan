package com.example.ritika.e_challan;

import android.content.Intent;
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
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.List;


public class ChallanUpdateFragment extends Fragment {




    ListView listViewUpdate;
    Spinner descriptionSpinner;
    String type;
    String description,fine1,fine2,fine3;
    Button updateButton;


    public ChallanUpdateFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_challan_update, container, false);

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        updateButton = (Button) getActivity().findViewById(R.id.button_update_challan);
        /*listViewUpdate=(ListView)getActivity().findViewById(R.id.listViewDescription);


        StrictMode.ThreadPolicy sp= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sp);

        Toast.makeText(getActivity(),"hellooo",Toast.LENGTH_LONG).show();*/



        updateButton.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {


                descriptionSpinner= (Spinner) getActivity().findViewById(R.id.SpinnerDescriptionChallan);
                get_description();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void get_description(){
        type = URLEncoder.encode(descriptionSpinner.getSelectedItem().toString());
        //Toast.makeText(getActivity(),type,Toast.LENGTH_LONG).show();
        try
        {

            //Toast.makeText(this,des,Toast.LENGTH_LONG).show();

            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/get_description_details.php?type="+type);
            // Log.v("error","http://studentportal.website/echallan/add_police_details.php?policeId="+policeId+"&password="+password+"&phoneNo"+phoneNo+"&name="+name);
            HttpResponse hr = hc.execute(hp);
            String result= EntityUtils.toString(hr.getEntity()).trim();
            //Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();

            JSONArray js= new JSONArray(result);
            Log.d("CHALLAN",js.toString()+"\n");

            for (int i=0;i<js.length();i++){
                JSONObject a=new JSONObject();
                a=js.getJSONObject(i);
                description = a.getString("des");
                fine1= a.getString("a");
                fine2= a.getString("b");
                fine3= a.getString("c");
            }

            Intent i= new Intent(getActivity(),DescriptionActivity.class);
            i.putExtra("description",description);
            i.putExtra("fine1",fine1);
            i.putExtra("fine2",fine2);
            i.putExtra("fine3",fine3);
            startActivity(i);
        }
        catch(Exception e)
        {
            Log.v("error :",e.toString());
        }

    }

}
