package com.example.ritika.e_challan;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
    }


}
