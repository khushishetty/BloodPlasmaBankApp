package com.example.bloodplasmabankapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodplasmabankapp.R;

public class CheckPlasmaEligibilityActivity extends Fragment {


    public CheckPlasmaEligibilityActivity() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_plasma_eligibility_activity, container, false);
    }
}