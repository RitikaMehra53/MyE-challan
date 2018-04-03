package com.example.ritika.e_challan;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

        policeIdEdit = (EditText) getActivity().findViewById(R.id.editTextDeletePoliceId);
        deletePoliceButton =(Button)getActivity().findViewById(R.id.buttonDeletePolice);


    }


}

