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

public class AddPoliceFragment extends Fragment {

    EditText policeIdEdit;
    EditText passwordEdit;
    EditText phoneNoEdit;
    EditText nameEdit;

    Button addButton;


    public AddPoliceFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_add_police, container, false);

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        policeIdEdit = (EditText) getActivity().findViewById(R.id.editTextAddPoliceId);
        passwordEdit = (EditText) getActivity().findViewById(R.id.editTextAddPolicePassword);
        phoneNoEdit = (EditText) getActivity().findViewById(R.id.editTextAddPolicePhoneNo);
        nameEdit = (EditText) getActivity().findViewById(R.id.editTextAddPoliceName);

        addButton = (Button) getActivity().findViewById(R.id.buttonAddPolice);

    }


}