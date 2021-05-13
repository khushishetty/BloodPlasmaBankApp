package com.example.bloodplasmabankapp.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.bloodplasmabankapp.R;

public class CheckBloodEligibilityFragment extends Fragment  {

    EditText weight, age;
    Spinner gender;
    CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9;
    String reason = "";
    String s_gender = null;
    AwesomeValidation awesomeValidation;
    Button btn;
    public CheckBloodEligibilityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_blood_eligibility, container, false);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        age = (EditText)view.findViewById(R.id.b_check_age);
        weight = (EditText)view.findViewById(R.id.b_check_weight);

        c1 = (CheckBox)view.findViewById(R.id.chk1);
        c2 = (CheckBox)view.findViewById(R.id.chk2);
        c3 = (CheckBox)view.findViewById(R.id.chk3);
        c4 = (CheckBox)view.findViewById(R.id.chk4);
        c5 = (CheckBox)view.findViewById(R.id.chk5);
        c6 = (CheckBox)view.findViewById(R.id.chk6);
        c7 = (CheckBox)view.findViewById(R.id.chk7);
        c8 = (CheckBox)view.findViewById(R.id.chk8);
        c9 = (CheckBox)view.findViewById(R.id.chk9);

        btn = (Button)view.findViewById(R.id.chk_btn1);

        gender = (Spinner)view.findViewById(R.id.b_check_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(container.getContext(),R.array.gender_option, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s_gender = parent.getItemAtPosition(position).toString();
                if(s_gender.equals("Female")){
                    c9.setVisibility(View.VISIBLE);
                }
                if(s_gender.equals("Male")){
                    c9.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s_gender = "Male";
            }
        });

        awesomeValidation.addValidation( age, RegexTemplate.NOT_EMPTY," PLEASE FILL THIS FIELD");
        awesomeValidation.addValidation(weight,RegexTemplate.NOT_EMPTY,"PLEASE FILL THIS FIELD");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(awesomeValidation.validate()){
                    if(Integer.parseInt(age.getText().toString())<18){
                       // Toast.makeText(container.getContext(), "", Toast.LENGTH_SHORT).show();
                        reason += "* You need to be above 18 years\n";
                    }
                    if(Integer.parseInt(weight.getText().toString())<40){
                        //Toast.makeText(container.getContext(), "Weight isn't sufficient", Toast.LENGTH_SHORT).show();
                        reason += "* Your weight needs to be above 40 kg\n";
                    }
                    if(c1.isChecked()){
                        //Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();
                        reason += "* Cannot donate if being treated with malaria in last 3 months\n";


                    }
                    if(c2.isChecked()){
                        //Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();
                        reason += "* Cannot donate if had tattoo or acupuncture in the last 3 months\n";
                    }
                    if(c3.isChecked()){
                        //Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();
                        reason += "* Cannot donate if being treated with insulin injections for Diabetics\n";
                    }
                    if(c4.isChecked()){
                        //Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();
                        reason += "* Cannot donate with any form of Cancer\n";
                    }
                    if(c5.isChecked()){
                        //Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();
                        reason += "* Cannot donate if having Hepatitis B, C, Tuberculosis, Leprosy, HIV.\n";
                    }
                    if(c6.isChecked()){
                        Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();

                    }
                    if(c7.isChecked()){
                        Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();
                        reason += "* Cannot donate with Hemoglobin less than 12.5 grams\n";
                    }
                    if(c8.isChecked()){
                        Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();
                        reason += "* Cannot donate with history of heart diseases\n";
                    }

                    if(c9.isChecked()){
                        Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();
                        reason += "* Can only donate if you have delivered more than a year ago\n";
                    }
                    if(!reason.equals("")){
                        new AlertDialog.Builder(container.getContext())
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
                    else {
                        new AlertDialog.Builder(container.getContext())
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




            }
        });

        return view;

    }


}