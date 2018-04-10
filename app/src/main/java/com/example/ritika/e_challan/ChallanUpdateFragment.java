package com.example.ritika.e_challan;

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
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChallanUpdateFragment extends Fragment {




    ListView listViewUpdate;


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
        listViewUpdate=(ListView)getActivity().findViewById(R.id.listViewDescription);


        StrictMode.ThreadPolicy sp= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sp);

        Toast.makeText(getActivity(),"hellooo",Toast.LENGTH_LONG).show();

        try
        {
            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/get_description_details.php");
            // Log.v("error","http://studentportal.website/echallan/add_police_details.php?policeId="+policeId+"&password="+password+"&phoneNo"+phoneNo+"&name="+name);
            HttpResponse hr = hc.execute(hp);
            String response= EntityUtils.toString(hr.getEntity()).trim();

            JSONArray js= new JSONArray(response);
            Log.d("CHALLAN",js.toString()+"\n");

            AllData allData=new AllData();
            for (int i=0;i<js.length();i++){
                JSONObject a=new JSONObject();
                a=js.getJSONObject(i);
                String description=a.getString("des");
                // Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                String first=a.getString("a");
                String second=a.getString("b");
                String third=a.getString("c");

                Toast.makeText(getActivity(),description+first+second+third,Toast.LENGTH_LONG).show();


                Data data2 = new Data();

                data2.des=description;
                data2.fine1=first;
                data2.fine2=second;
                data2.fine3=third;

                allData.details.add(data2);
                //Toast.makeText(getActivity(),allData.details.size(),Toast.LENGTH_LONG).show();

            }

            //Toast.makeText(getActivity(),allData.details.size(),Toast.LENGTH_LONG).show();

           // DescriptionAdapter descriptionAdapter=new DescriptionAdapter(getActivity(),allData.details);
            //listViewUpdate.setAdapter(descriptionAdapter);


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


        updateButton.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {


                updateChallan();


            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateChallan(){



    }



}
