package com.example.ritika.e_challan;

import android.content.Intent;
import android.icu.text.TimeZoneFormat;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.SimpleTimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterChallanFragment  extends Fragment {

    Spinner placeListEdit;
    Spinner descriptionListEdit;
    EditText nameEdit;
    EditText phoneNoEdit;
    EditText rcNoEdit;
    EditText licenceNoEdit;
    Button register;
int cNo=000001;
    public RegisterChallanFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_challan, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        register=(Button) getActivity().findViewById(R.id.buttonRegisterChallan);

        register.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                nameEdit=(EditText)getActivity().findViewById(R.id.editTextName);
                phoneNoEdit=(EditText)getActivity().findViewById(R.id.editTextPhoneNo);
                rcNoEdit=(EditText)getActivity().findViewById(R.id.editTextRCNo);
                licenceNoEdit=(EditText)getActivity().findViewById(R.id.editTextLicenseNo);
                placeListEdit= (Spinner) getActivity().findViewById(R.id.SpinnerOffencePlace);
                descriptionListEdit= (Spinner) getActivity().findViewById(R.id.SpinnerOffenceDescription);


                register();

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void register()
    {
        StrictMode.ThreadPolicy sp= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sp);
            cNo++;



        String challanNo = Integer.toString(cNo);


        String name= URLEncoder.encode(nameEdit.getText().toString());
        String phoneNo=phoneNoEdit.getText().toString();
        String rcNo= URLEncoder.encode(rcNoEdit.getText().toString());
        String licenceNo= URLEncoder.encode(licenceNoEdit.getText().toString());
        String des= URLEncoder.encode(descriptionListEdit.getSelectedItem().toString());
        String place= URLEncoder.encode(placeListEdit.getSelectedItem().toString());

        Calendar calender = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(calender.getTimeInMillis()).toString();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");

        String time1 = timeFormat.format(cal.getTime()).toString();
        Toast.makeText(getActivity(),"Name: \n" + name + ", phone no: " + phoneNo + ", Rc no: " + rcNo + ", licence no: "
                + licenceNo + ", description: "
                + des + ", place: " + place + ", date: " + date + ", time: " + time1 + ", challanNo"

                +challanNo ,Toast.LENGTH_LONG).show();



        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");

        Matcher rc = pattern.matcher(rcNo);
        Matcher licence = pattern.matcher(licenceNo);

        // Matcher pass = pattern.matcher(password);
        if (name.isEmpty()) {
            Toast.makeText(getActivity(), "Invalid name", Toast.LENGTH_LONG).show();
        }
        if (phoneNo.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter phone number", Toast.LENGTH_LONG).show();
        }
        if(phoneNo.length()!=10 )
        {
            Toast.makeText(getActivity(), "Invalid phone number", Toast.LENGTH_LONG).show();

        }

         if(rc.matches())
        {
            Toast.makeText(getActivity(), "Invalid RC number ", Toast.LENGTH_LONG).show();

        }


        else if (rcNo.isEmpty()) {
            Toast.makeText(getActivity(), "Invalid RC number", Toast.LENGTH_LONG).show();
        }

        else {



        try{


            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/reg.php?challanNo="+challanNo+"&name="+name+"&phoneNo="+phoneNo+"&rcNo="+rcNo+"&licenceNo="+licenceNo+"&place="+place+"&des1="+des);
        //    Log.v("error","http://studentportal.website/echallan/reg.php?challanNo="+challanNo+"&name="+name+"&phoneNo="+phoneNo+"&rcNo="+rcNo+"&licenceNo="+licenceNo+"&date="+date+"&time="+time+"&description="+description+"&place="+place);


            Toast.makeText(getActivity(),"Hello try",Toast.LENGTH_LONG).show();


            HttpResponse hr = hc.execute(hp);
            String result= EntityUtils.toString(hr.getEntity()).trim();

                //Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();


            Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();


            if(result.equals("ok"))
            {
                Toast.makeText(getActivity(),"Succesfully Registered",Toast.LENGTH_LONG).show();
                //Intent i= new Intent(getActivity(),PolicePortalActivity.class);
                //startActivity(i);


                } else if(result.equals("not ok")) {
                    //progress.dismiss();
                    Toast.makeText(getActivity(), "Try Again!", Toast.LENGTH_LONG).show();
                }
                else if(result.equals("not possible"))
                {
                    Toast.makeText(getActivity(), "This person has already voilated this rule 3 times", Toast.LENGTH_LONG).show();


                }

            } catch (Exception e) {
                Toast.makeText(getActivity(), "Error found ! Please try it after sometime", Toast.LENGTH_LONG).show();
                //Log.v("error :",e.toString());

            }


        }

    }
}
