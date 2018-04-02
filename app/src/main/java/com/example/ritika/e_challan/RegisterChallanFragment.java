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

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.SimpleTimeZone;

public class RegisterChallanFragment  extends Fragment {

    Spinner placeListEdit;
    Spinner descriptionListEdit;
    EditText nameEdit;
    EditText phoneNoEdit;
    EditText rcNoEdit;
    EditText licenceNoEdit;
    TimePicker pickerTimeEdit;
    DatePicker pickerDateEdit;
    Button register;

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
                pickerDateEdit=(DatePicker)getActivity().findViewById(R.id.datePickerChallanDate);
                pickerTimeEdit=(TimePicker)getActivity().findViewById(R.id.timePickerChallanTime);

                StrictMode.ThreadPolicy sp= new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(sp);

                register();

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void register()
    {
// create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 0 to 999
        int cNo = rand.nextInt(10000);
        String challanNo= Integer.toString(cNo);

        String name= URLEncoder.encode(nameEdit.getText().toString());
        String phoneNo=phoneNoEdit.getText().toString();
        String rcNo= URLEncoder.encode(rcNoEdit.getText().toString());
        String licenceNo= URLEncoder.encode(licenceNoEdit.getText().toString());
        String description= URLEncoder.encode(descriptionListEdit.getSelectedItem().toString());
        String place= URLEncoder.encode(placeListEdit.getSelectedItem().toString());

        java.util.Calendar calender = java.util.Calendar.getInstance();
        calender.clear();
        calender.set(java.util.Calendar.MONTH, pickerDateEdit.getMonth());
        calender.set(java.util.Calendar.DAY_OF_MONTH, pickerDateEdit.getDayOfMonth());
        calender.set(java.util.Calendar.YEAR, pickerDateEdit.getYear());

        SimpleDateFormat dateFormatter = new SimpleDateFormat(getString(R.string.dateformate));
        String date = dateFormatter.format(new Date(calender.getTimeInMillis())).toString();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        String time = timeFormat.format(cal.getTime()).toString();
        Toast.makeText(getActivity(),"Name: \n" + name + ", phone no: " + phoneNo + ", Rc no: " + rcNo + ", licence no: "
                + licenceNo + ", description: "
                + description + ", place: " + place + ", date: " + date + ", time: " + time + ", challanNo"
                +challanNo ,Toast.LENGTH_LONG).show();

        try
        {
            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/reg.php?challan="+challanNo+"&name="+name);
           // Log.v("error","http://studentportal.website/echallan/reg.php?challanNo="+challanNo+"&name="+name+"&phoneNo="+phoneNo+"&rcNo="+rcNo+"&licenceNo="+licenceNo+"&date="+date+"&time="+time+"&description="+description+"&place="+place);

            HttpResponse hr = hc.execute(hp);
            String response= EntityUtils.toString(hr.getEntity()).trim();
            System.out.print(response);

            if(response.equals("ok"))
            {
                Intent i= new Intent(getActivity(),PolicePortalActivity.class);
                startActivity(i);

            }
            else
            {
                //progress.dismiss();
                Toast.makeText(getActivity(),"Try Again!",Toast.LENGTH_LONG).show();
            }

        }
        catch(Exception e)
        {
            Log.v("error :",e.toString());
        }
    }
}
