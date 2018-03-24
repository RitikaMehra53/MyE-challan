package com.example.ritika.e_challan;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterChallanFragment  extends Fragment {

    Spinner placeList;
    Spinner descriptionList;

    public RegisterChallanFragment() {
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
        return inflater.inflate(R.layout.fragment_register_challan, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        placeList= (Spinner) getActivity().findViewById(R.id.SpinnerOffencePlace);
        ArrayAdapter<CharSequence> placeAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.district_arrays, android.R.layout.simple_spinner_item);
        placeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placeList.setAdapter(placeAdapter); // Apply the adapter to the spinner

        descriptionList= (Spinner) getActivity().findViewById(R.id.SpinnerOffenceDescription);
        ArrayAdapter<CharSequence> descriptionAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.challan_description_arrays, android.R.layout.simple_spinner_item);
        descriptionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Specify the layout to use when the list of choices appears
        descriptionList.setAdapter(descriptionAdapter); // Apply the adapter to the spinner


    }
}
