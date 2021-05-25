package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Patterns;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestRegisterActivity extends AppCompatActivity  {

    Spinner spinner,spinner_type;
    String choice_bld_grp,choice_type, sname, sphno, semail, saddress, surgency, spass, stime, sage, srepass;
    EditText name, pass, phno, email, address, age, repass;
    CheckBox urgency;
    Button  submit;
    TextView login;
    AwesomeValidation awesomeValidation;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^"+
                    "(?=.*[0-9])" +                 //At least one digit.
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +              //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 4 characters
                    "$");



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
        age = (EditText)findViewById(R.id.req_reg_age_id);
        urgency = (CheckBox)findViewById(R.id.req_reg_urgent_id);
        login = (TextView) findViewById(R.id.req_log_btn);
        submit = (Button)findViewById(R.id.req_reg_btn);
        repass= (EditText)findViewById(R.id.req_reg_repass_id);

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
                sage = age.getText().toString();
                srepass = repass.getText().toString();

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                stime = df.format(c.getTime());

                if(urgency.isChecked()){
                    surgency = "Yes";
                }
                else{
                    surgency = "No";
                }
                if(awesomeValidation.validate() && validateMobile(sphno) && validateEmail(semail) && validatePassword(spass) && srepass.equals(spass)){
                    Db_Helper_Requests helper = new Db_Helper_Requests(getApplicationContext());
                    int b = helper.insertRequest(sname, sphno, choice_bld_grp, semail, saddress, choice_type, surgency, spass, stime, sage);

                    if(b == 1){
                        Toast.makeText(RequestRegisterActivity.this, "Request made successfully!!", Toast.LENGTH_SHORT).show();

                        /*
                        SmsManager manager = SmsManager.getDefault();
                        manager.sendTextMessage(sphno, null, "Your request has been successfully made!",null, null);

                         */
                        SmsManager manager = SmsManager.getDefault();
                        manager.sendTextMessage(sphno, null, "Your request has been made successfully!!",null, null);
                    }
                    else if(b==2){
                        Toast.makeText(RequestRegisterActivity.this, "Request Failed!!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(RequestRegisterActivity.this, "The phone number is already registered!!", Toast.LENGTH_SHORT).show();
                    }
                }if(!validateMobile(sphno)){
                    phno.setError("Invalid phone number !!");
                }
                if(!validateEmail(semail)){
                    email.setError("Invalid email !!");
                }if(!validatePassword(spass)){
                    pass.setError("Weak Password!!");
                }if(!srepass.equals(spass)){
                    repass.setError("Password not equal");
                }

                
            }
        });
    }

    boolean validateMobile(String input){
        Pattern p = Pattern.compile("[6-9][0-9]{9}");
        Matcher m = p.matcher(input);
        return m.matches();
    }
    boolean validateEmail(String mail){
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    boolean validatePassword(String p1){
        /*
        if(!PASSWORD_PATTERN.matcher(p1).matches()){
            pass.setError("Password too weak !!");
        }*/
        return PASSWORD_PATTERN.matcher(p1).matches();
    }

    
}