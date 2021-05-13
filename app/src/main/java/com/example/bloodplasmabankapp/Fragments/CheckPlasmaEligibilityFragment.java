package com.example.bloodplasmabankapp.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BlendMode;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.bloodplasmabankapp.R;

public class CheckPlasmaEligibilityFragment extends Fragment {



    CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9;
    Button btn;
    public CheckPlasmaEligibilityFragment() {
        // Required empty public constructor
    }
    String reason = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_check_plasma_eligibility_activity, container, false);
        c1 = (CheckBox)view.findViewById(R.id.checkBox);
        c2 = (CheckBox)view.findViewById(R.id.checkBox2);
        c3 = (CheckBox)view.findViewById(R.id.checkBox3);
        c4 = (CheckBox)view.findViewById(R.id.checkBox4);
        c5 = (CheckBox)view.findViewById(R.id.checkBox5);
        c6 = (CheckBox)view.findViewById(R.id.checkBox6);
        c7 = (CheckBox)view.findViewById(R.id.checkBox7);
        c8 = (CheckBox)view.findViewById(R.id.checkBox8);
        c9 = (CheckBox)view.findViewById(R.id.checkBox9);

        btn = (Button)view.findViewById(R.id.chk_plasma_eligibility_id);

        c8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(c8.isChecked()){

                    c6.setEnabled(true);

                }
                else{
                    c6.setChecked(false);
                    c6.setEnabled(false);

                }
            }
        });
        c9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(c9.isChecked()){
                    c7.setEnabled(true);

                }
                else{
                    c7.setChecked(false);
                    c7.setEnabled(false);

                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!c1.isChecked()){
                    reason += "* Must be at least 18 years of age\n";
                }
                if(!c2.isChecked()){
                    reason += "* Must weight more than 50 kilograms\n";
                }
                if(!c3.isChecked()){
                    reason += "* Must pass the medical examination\n";
                }
                if(!c4.isChecked()){
                    reason += "* Mst complete the medical screening\n";
                }
                if(!c5.isChecked()){
                    reason += "* Must be non-reactive to transmissible viruses\n";
                }

                if(!c8.isChecked()){
                    reason += "* Must have recovered from Covid-19 to donate\n";
                }
                if(c8.isChecked()){
                    if(!c6.isChecked()){
                        reason += "* Must donate after 14 days of a Covid-19 positive report ( if ASYMPTOMATIC ) OR after 14 days of symptom resolution ( IF SYMPTOMATIC )\n";
                    }
                }
                if(c9.isChecked()){
                    if(!c7.isChecked()){
                        reason += "* Cannot donate within 28 days of receiving Covid-19 vaccination\n";
                    }
                }

                if(!reason.equals("")){
                    new AlertDialog.Builder(container.getContext()).setIcon(R.drawable.ic_baseline_priority_high_24)
                            .setTitle("Sorry!! You are not eligible for donation")
                            .setMessage(reason)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    reason = "";
                                    dialog.dismiss();
                                }
                            }).show();
                }
                else{
                    new AlertDialog.Builder(container.getContext()).setIcon(R.drawable.ic_baseline_done_24)
                            .setTitle("Congratulations!! ")
                            .setMessage("You are eligible to donate!!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            }
        });

        return view;
    }
}