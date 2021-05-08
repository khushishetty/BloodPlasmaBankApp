package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.bloodplasmabankapp.DB.Db_Helper_Requests;

public class RequestRegisterActivity extends AppCompatActivity  {

    Spinner spinner,spinner_type;
    String choice_bld_grp,choice_type, sname, sphno, semail, saddress, surgency, spass;
    EditText name, pass, phno, email, address;
    CheckBox urgency;
    Button  submit;
    TextView login;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_register);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        name = (EditText)findViewById(R.id.req_reg_name_id);
        pass = (EditText)findViewById(R.id.req_reg_pass_id);
        phno = (EditText)findViewById(R.id.req_reg_phno_id);
        email = (EditText)findViewById(R.id.req_reg_email_id);
        address = (EditText)findViewById(R.id.req_reg_address_id);

        urgency = (CheckBox)findViewById(R.id.req_reg_urgent_id);
        login = (TextView) findViewById(R.id.req_log_btn);
        submit = (Button)findViewById(R.id.req_reg_btn);

        spinner = (Spinner)findViewById(R.id.req_reg_bloodgrp_id);
        spinner_type = (Spinner)findViewById(R.id.spinner_type_id);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.blood_grp_option, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spinner.setAdapter(adapter1);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.type_option, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spinner_type.setAdapter(adapter);
        
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_bld_grp = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                choice_bld_grp = "blood";
            }
        });
        
        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_type = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                choice_type = "Blood";
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestRegisterActivity.this,RequestLoginActivity.class);
                startActivity(intent);
            }
        });

        awesomeValidation.addValidation( name, RegexTemplate.NOT_EMPTY," PLEASE FILL THIS FIELD");
        awesomeValidation.addValidation(phno,RegexTemplate.NOT_EMPTY,"PLEASE FILL THIS FIELD");
        awesomeValidation.addValidation( pass, RegexTemplate.NOT_EMPTY," PLEASE FILL THIS FIELD");
        awesomeValidation.addValidation(email,RegexTemplate.NOT_EMPTY,"PLEASE FILL THIS FIELD");
        awesomeValidation.addValidation( address, RegexTemplate.NOT_EMPTY," PLEASE FILL THIS FIELD");



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sname = name.getText().toString();
                spass = pass.getText().toString();
                sphno = phno.getText().toString();
                saddress = address.getText().toString();
                semail = email.getText().toString();
                if(urgency.isChecked()){
                    surgency = "Yes";
                }
                else{
                    surgency = "No";
                }
                if(awesomeValidation.validate()){
                    Db_Helper_Requests helper = new Db_Helper_Requests(getApplicationContext());
                    boolean b = helper.insertRequest(sname, sphno, choice_bld_grp, semail, saddress, choice_type, surgency, spass);

                    if(b){
                        Toast.makeText(RequestRegisterActivity.this, "Request made successfully!!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(RequestRegisterActivity.this, "Request Failed!!", Toast.LENGTH_SHORT).show();
                    }
                }

                
            }
        });
    }

    
}