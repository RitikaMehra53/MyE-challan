package com.example.ritika.e_challan;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdatePoliceFragment extends Fragment {


    EditText policeIdEdit;

    Button updateButton;


    public UpdatePoliceFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_update_police, container, false);



       return v;

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        policeIdEdit = (EditText) getActivity().findViewById(R.id.editTextUpdatePoliceId);


        Button b = (Button) getActivity().findViewById(R.id.buttonUpdatePoliceDetails);


        b.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(getActivity(),UpdatePoliceActivity.class);
                startActivity(intent);




            }
        });

    }



}

