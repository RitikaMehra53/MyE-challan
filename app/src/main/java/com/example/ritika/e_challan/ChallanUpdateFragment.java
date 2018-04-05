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

public class ChallanUpdateFragment extends Fragment {

    EditText rcEdit;
    EditText rcFirstEdit;
    EditText rcSecondEdit;
    EditText rcThirdEdit;

    EditText helmetEdit;
    EditText helmetFirstEdit;
    EditText helmetSecondEdit;
    EditText helmetThirdEdit;

    EditText pollutionEdit;
    EditText pollutionFirstEdit;
    EditText pollutionSecondEdit;
    EditText pollutionThirdEdit;

    EditText licenceEdit;
    EditText licenceFirstEdit;
    EditText licenceSecondEdit;
    EditText licenceThirdEdit;

    EditText insuranceEdit;
    EditText insuranceFirstEdit;
    EditText insuranceSecondEdit;
    EditText insuranceThirdEdit;

    EditText missbehaveEdit;
    EditText missbehaveFirstEdit;
    EditText missbehaveSecondEdit;
    EditText missbehaveThirdEdit;

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
        Toast.makeText(getActivity(),"hellooo",Toast.LENGTH_LONG).show();

        updateButton.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                rcFirstEdit =(EditText) getActivity().findViewById(R.id.rcFineFirst);
                rcSecondEdit =(EditText) getActivity().findViewById(R.id.rcFineSecond);
                rcThirdEdit =(EditText) getActivity().findViewById(R.id.rcFineThird);

                helmetFirstEdit=(EditText) getActivity().findViewById(R.id.helmetFineFirst);
                helmetSecondEdit=(EditText) getActivity().findViewById(R.id.helmetFineSecond);
                helmetThirdEdit=(EditText) getActivity().findViewById(R.id.helmetFineThird);

                pollutionFirstEdit=(EditText) getActivity().findViewById(R.id.pollutionFineFirst);
                pollutionSecondEdit=(EditText) getActivity().findViewById(R.id.pollutionFineSecond);
                pollutionThirdEdit=(EditText) getActivity().findViewById(R.id.pollutionFineThird);

                licenceFirstEdit=(EditText) getActivity().findViewById(R.id.licenceFineFirst);
                licenceSecondEdit=(EditText) getActivity().findViewById(R.id.licenceFineSecond);
                licenceThirdEdit=(EditText) getActivity().findViewById(R.id.licenceFineThird);

                insuranceFirstEdit=(EditText) getActivity().findViewById(R.id.insuranceFineFirst);
                insuranceSecondEdit=(EditText) getActivity().findViewById(R.id.insuranceFineSecond);
                insuranceThirdEdit=(EditText) getActivity().findViewById(R.id.insuranceFineThird);

                missbehaveFirstEdit=(EditText) getActivity().findViewById(R.id.missbehaveFineFirst);
                missbehaveSecondEdit=(EditText) getActivity().findViewById(R.id.missbehaveFineSecond);
                missbehaveThirdEdit=(EditText) getActivity().findViewById(R.id.missbehaveFineThird);

                rcEdit =(EditText)getActivity().findViewById(R.id.editTextUpdateRc);
                helmetEdit =(EditText)getActivity().findViewById(R.id.editTextUpdateHelmet);
                pollutionEdit =(EditText)getActivity().findViewById(R.id.editTextUpdatePollution);
                licenceEdit =(EditText)getActivity().findViewById(R.id.editTextUpdateLicence);
                insuranceEdit =(EditText)getActivity().findViewById(R.id.editTextUpdateInsurance);
                missbehaveEdit =(EditText)getActivity().findViewById(R.id.editTextUpdateMissbehave);
                try
                {
                    HttpClient hc= new DefaultHttpClient();
                    HttpPost hp=new HttpPost("http://studentportal.website/echallan/get_description_details.php");
                    // Log.v("error","http://studentportal.website/echallan/add_police_details.php?policeId="+policeId+"&password="+password+"&phoneNo"+phoneNo+"&name="+name);
                    HttpResponse hr = hc.execute(hp);
                    String response= EntityUtils.toString(hr.getEntity()).trim();
                    //System.out.print(response);

                    JSONObject js= new JSONObject(response);
                    JSONArray data = js.getJSONArray("data");

                    Toast.makeText(getActivity(),"before loop",Toast.LENGTH_LONG).show();

                    // looping through All Contacts
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject c = data.getJSONObject(i);

                        String des = c.getString("des");
                        String first = c.getString("a");
                        String second = c.getString("b");
                        String third = c.getString("c");

                        Toast.makeText(getActivity(),des+first+second+third,Toast.LENGTH_LONG).show();
                    }

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
                update();

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void update()
    {
        StrictMode.ThreadPolicy sp= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sp);


        Toast.makeText(getActivity(),"byee",Toast.LENGTH_LONG).show();

        try
        {
            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/get_description_details.php");
            // Log.v("error","http://studentportal.website/echallan/add_police_details.php?policeId="+policeId+"&password="+password+"&phoneNo"+phoneNo+"&name="+name);
            HttpResponse hr = hc.execute(hp);
            String response= EntityUtils.toString(hr.getEntity()).trim();
            //System.out.print(response);

            JSONObject js= new JSONObject(response);
            JSONArray data = js.getJSONArray("data");
            Log.d("APP",data.toString());

            // looping through All Contacts
            for (int i = 0; i < data.length(); i++) {
                JSONObject c = data.getJSONObject(i);

                String des = c.getString("des");
                String first = c.getString("a");
                String second = c.getString("b");
                String third = c.getString("c");

                Toast.makeText(getActivity(),des+first+second+third,Toast.LENGTH_LONG).show();
            }

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
