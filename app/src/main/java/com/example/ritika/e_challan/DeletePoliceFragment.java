package com.example.ritika.e_challan;

import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeletePoliceFragment extends Fragment {


    EditText policeIdEdit;
    Button deletePoliceButton;

    public DeletePoliceFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_delete_police, container, false);

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        deletePoliceButton =(Button)getActivity().findViewById(R.id.buttonDeletePolice);
        deletePoliceButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                policeIdEdit = (EditText) getActivity().findViewById(R.id.editTextDeletePoliceId);
                delete();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void delete() {

        StrictMode.ThreadPolicy sp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sp);
        String policeId = URLEncoder.encode(policeIdEdit.getText().toString());
// create instance of Random class
        Pattern pattern = Pattern.compile("[a-z0-9]*");

        Matcher pId = pattern.matcher(policeId);
        // Matcher pass = pattern.matcher(password);

        if (policeId.isEmpty()) {
            Toast.makeText(getActivity(), "Enter police Id", Toast.LENGTH_LONG).show();
        }
        else if(!policeId.matches("[a-zA-Z0-9]*"))
        {
            Toast.makeText(getActivity(), "Enter valid police id", Toast.LENGTH_LONG).show();
        }

        else {
            try {


                HttpClient hc = new DefaultHttpClient();
                HttpPost hp = new HttpPost("http://studentportal.website/echallan/delete_police_details.php?policeId=" + policeId);
                //    Log.v("error","http://studentportal.website/echallan/reg.php?challanNo="+challanNo+"&name="+name+"&phoneNo="+phoneNo+"&rcNo="+rcNo+"&licenceNo="+licenceNo+"&date="+date+"&time="+time+"&description="+description+"&place="+place);
                HttpResponse hr = hc.execute(hp);
                String result = EntityUtils.toString(hr.getEntity()).trim();

                //  Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();

                if (result.equals("ok")) {
                    Toast.makeText(getActivity(), "Police id deleted", Toast.LENGTH_LONG).show();
                    policeIdEdit.setText("");


                } else if (result.equals("not ok")) {
                    Toast.makeText(getActivity(), "Enter valid Police id", Toast.LENGTH_LONG).show();


                }


            } catch (Exception e) {
                Toast.makeText(getActivity(), "Error found. Try again!", Toast.LENGTH_LONG).show();
                //Log.v("error :",e.toString());
            }
        }
    }
}

