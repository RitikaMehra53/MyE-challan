package com.example.ritika.e_challan;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.net.URLEncoder;

public class PendingChallanFragment extends Fragment {
    String rc;
    String phoneNo,name,rcN,licenceNo,place,description,challanNo,payable,fineAmount,date1,time1;

    EditText rcNo;

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


    }
    public void get_pending_challans(View v){

        rc= URLEncoder.encode(rcNo.getText().toString());


        try{


            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/pending.php?rcNo="+rc);
            //    Log.v("error","http://studentportal.website/echallan/reg.php?challanNo="+challanNo+"&name="+name+"&phoneNo="+phoneNo+"&rcNo="+rcNo+"&licenceNo="+licenceNo+"&date="+date+"&time="+time+"&description="+description+"&place="+place);


            Toast.makeText(getActivity(),"Hello try",Toast.LENGTH_LONG).show();

            HttpResponse hr = hc.execute(hp);
            String result= EntityUtils.toString(hr.getEntity()).trim();

            Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();

            String response= EntityUtils.toString(hr.getEntity());
            Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
            JSONObject js= new JSONObject(response);
            name= js.getString("name");
            challanNo = js.getString("challanNo");
            phoneNo = js.getString("phoneNo");
            rcN = js.getString("rcN");
            licenceNo = js.getString("licenceNo");
            date1 = js.getString("date1");
            time1 = js.getString("time1");
            description =js.getString("description");
            place = js.getString("place");
            payable = js.getString("payable");
            fineAmount = js.getString("fineAmount");

            Toast.makeText(getActivity(),"Print Values ---"+name +challanNo+phoneNo +rcN+licenceNo+date1+time1+description+place+payable+fineAmount,Toast.LENGTH_LONG).show();


                /*e13.setText(full_name);
                e14.setText(email);

                e16.setText(contact_no);
                e17.setText(age);
                e19.setText(weight);
                e20.setText(address);
                e21.setText(blood_group);
                e22.setText(hb);*/



        }
        catch(Exception e)
        {
            Toast.makeText(getActivity(),"Catchhh",Toast.LENGTH_LONG).show();
            //Log.v("error :",e.toString());
        }
    }


}
