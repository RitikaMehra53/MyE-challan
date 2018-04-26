package com.example.ritika.e_challan;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Spinner;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.R.attr.description;

public class ChallanHistoryFragment extends Fragment {

    String rc;
    //String phoneNo,name,rcN,licenceNo,place,description,challanNo,payable,fineAmount,date1,time1;

    EditText rcNoHis;
    Button searchButton;
    ListView  listView;

    public ChallanHistoryFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_challan_history, container, false);

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        searchButton=(Button) getActivity().findViewById(R.id.buttonSearchChallanHistory);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                rcNoHis =(EditText)getActivity().findViewById(R.id.editTextChallanHistoryRCNo);

                rc= URLEncoder.encode(rcNoHis.getText().toString());
                Intent i= new Intent(getActivity(),HistoryActivity.class);
                i.putExtra("rc",rc);
                startActivity(i);

            }
        });

    }


    /*@RequiresApi(api = Build.VERSION_CODES.N)
    public void searchHistory(){

        rc= URLEncoder.encode(rcNo.getText().toString());

        if (rc.isEmpty() || rc.matches("[A-Za-z0-9]{10}")) {
            Toast.makeText(getActivity(), "Invalid police id", Toast.LENGTH_LONG).show();
        }


        try{


            HttpClient hc= new DefaultHttpClient();
            HttpPost hp=new HttpPost("http://studentportal.website/echallan/hist.php?rcNo="+rc);
            //    Log.v("error","http://studentportal.website/echallan/reg.php?challanNo="+challanNo+"&name="+name+"&phoneNo="+phoneNo+"&rcNo="+rcNo+"&licenceNo="+licenceNo+"&date="+date+"&time="+time+"&description="+description+"&place="+place);


           // Toast.makeText(getActivity(),"Hello try",Toast.LENGTH_LONG).show();

            HttpResponse hr = hc.execute(hp);
            int status = hr.getStatusLine().getStatusCode();
            //String result= EntityUtils.toString(hr.getEntity()).trim();

            if (status == 200) {
                HttpEntity entity = hr.getEntity();
                String data = EntityUtils.toString(entity);


                JSONArray js= new JSONArray(data);
                Log.d("CHALLAN",js.toString()+"\n");


                for (int i = 0; i < js.length(); i++) {
                    JSONObject object = js.getJSONObject(i);

                    Data data1 = new Data();

                    data1.setChallanNo(object.getString("challanNo"));
                    data1.setName(object.getString("name"));
                    data1.setLicenceNo(object.getString("licenceNo"));
                    data1.setDate1(object.getString("date1"));
                    data1.setTime1(object.getString("time1"));
                    data1.setPlace(object.getString("place"));
                    data1.setDescription(object.getString("description"));
                    dataList.add(data1);
                }
            }



        } catch (ParseException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

















        //Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();


           *//* JSONArray js= new JSONArray(result);
            Log.d("CHALLAN",js.toString()+"\n");


            AllData allData=new AllData();
            for (int i=0;i<js.length();i++){
                JSONObject a=new JSONObject();
                a=js.getJSONObject(i);
                String name=a.getString("name");
                // Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                String challanNo=a.getString("challanNo");
                String date1=a.getString("date1");
                String place=a.getString("place");

                String time1=a.getString("time1");
                // Toast.makeText(getActivity(),challanNo+time1+date1+place,Toast.LENGTH_LONG).show();

                String licenceNo=a.getString("licenceNo");
                String rcN=a.getString("rcN");
                String description=a.getString("description");
                String fineAmount=a.getString("fineAmount");

                String payable=a.getString("payable");
                String phoneNo=a.getString("phoneNo");
                //Toast.makeText(getActivity(),licenceNo+rcN+description+fineAmount,Toast.LENGTH_LONG).show();
                //  Toast.makeText(getActivity(),payable+phoneNo,Toast.LENGTH_LONG).show();

                Data data1 = new Data();

                data1.challanNo=challanNo;
                data1.name=name;
                data1.date1=date1;
                data1.description=description;
                data1.fineAmount=fineAmount;
                data1.licenceNo=licenceNo;
                data1.payable=payable;
                data1.phoneNo=phoneNo;
                data1.time1=time1;
                data1.place=place;
                data1.rcNo=rcN;

                allData.details.add(data1);

                Toast.makeText(getActivity(),"Details......... "+allData.details,Toast.LENGTH_LONG).show();



            }

            HistoryBaseAdapter historyBaseAdapter=new HistoryBaseAdapter(getActivity(),allData.details);
            listView.setAdapter(historyBaseAdapter);*//*
            *//*challanNo="101";
            name="ABC";;
            date1="2/3/18";
            description="Helmet";
            fineAmount="300";
            licenceNo="lic123";
            payable="No";
            phoneNo="1234456666";
            time1="12:45:50";
            place="Ambala";
            rcN="RC123";*//*

                *//*e13.setText(full_name);
                e14.setText(email);

                e16.setText(contact_no);
                e17.setText(age);
                e19.setText(weight);
                e20.setText(address);
                e21.setText(blood_group);
                e22.setText(hb);*//*

        *//*}
        catch(Exception e)
        {
            Toast.makeText(getActivity(),"Catchhh",Toast.LENGTH_LONG).show();
            //Log.v("error :",e.toString());
        }*//*
    }
*/
}


