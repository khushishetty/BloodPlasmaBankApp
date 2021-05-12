package com.example.bloodplasmabankapp;

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

public class CheckBloodEligibilityFragment extends Fragment  {

    EditText weight, age;
    Spinner gender;
    CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9;

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
                        Toast.makeText(container.getContext(), "", Toast.LENGTH_SHORT).show();
                    }
                    if(Integer.parseInt(weight.getText().toString())<40){
                        Toast.makeText(container.getContext(), "Weight isn't sufficient", Toast.LENGTH_SHORT).show();
                    }
                    if(c1.isChecked()){
                        Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();

                    }
                    if(c2.isChecked()){
                        Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();

                    }
                    if(c3.isChecked()){
                        Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();

                    }
                    if(c4.isChecked()){
                        Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();

                    }
                    if(c5.isChecked()){
                        Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();

                    }
                    if(c6.isChecked()){
                        Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();

                    }
                    if(c7.isChecked()){
                        Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();

                    }
                    if(c8.isChecked()){
                        Toast.makeText(container.getContext(), "Not eligible", Toast.LENGTH_SHORT).show();

                    }
                }




            }
        });

        return view;

    }


}