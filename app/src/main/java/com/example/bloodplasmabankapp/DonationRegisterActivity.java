package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.example.bloodplasmabankapp.DB.DBHelper;
import com.example.bloodplasmabankapp.DB.DB_Plasma_Helper;
import com.example.bloodplasmabankapp.DB.Db_Helper_Requests;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DonationRegisterActivity extends AppCompatActivity {


    Spinner spinner, gender;
    String choice_bld_grp, sname, sphno, semail, saddress, spass, sage, sailments, sgender, srepass;
    EditText name, pass, phno, email, address, age, ailments,repass_et;
    TextInputLayout repass;
    CheckBox diabetes, hemoglobin, diseases;
    Button  submit;
    AwesomeValidation awesomeValidation;
    TextView btn;

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
        setContentView(R.layout.activity_donation_register);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        name = (EditText)findViewById(R.id.donate_reg_name_id);
        pass = (EditText)findViewById(R.id.donate_reg_pass_id);
        phno = (EditText)findViewById(R.id.donate_reg_phno_id);
        email = (EditText)findViewById(R.id.donate_reg_email_id);
        address = (EditText)findViewById(R.id.donate_reg_address_id);
        age = (EditText)findViewById(R.id.donate_reg_age_id);
        diabetes = (CheckBox)findViewById(R.id.donate_reg_diabetes_id);
        submit = (Button)findViewById(R.id.donate_reg_btn);
        ailments = (EditText)findViewById(R.id.donate_reg_ailments_id);
        spinner = (Spinner)findViewById(R.id.donate_reg_bloodgrp_id);
        repass = (TextInputLayout) findViewById(R.id.donate_reg_re_pass_lay_id);
        repass_et = (TextInputEditText)findViewById(R.id.donate_reg_re_pass_id);
        gender = (Spinner)findViewById(R.id.donate_gender_id);
        hemoglobin = (CheckBox)findViewById(R.id.donate_reg_hemoglobin_id);
        diseases = (CheckBox)findViewById(R.id.donate_reg_diseases_id);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.blood_grp_option, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter1);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.gender_option, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gender.setAdapter(adapter2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_bld_grp = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sgender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sgender = "Female";
            }
        });


        btn = (TextView)findViewById(R.id.bbbbb);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonationRegisterActivity.this, DonationLoginActivity.class));
            }
        });


        awesomeValidation.addValidation( name, RegexTemplate.NOT_EMPTY," PLEASE FILL THIS FIELD");
        awesomeValidation.addValidation(phno,RegexTemplate.NOT_EMPTY,"PLEASE FILL THIS FIELD");
        awesomeValidation.addValidation( pass, RegexTemplate.NOT_EMPTY," PLEASE FILL THIS FIELD");
        awesomeValidation.addValidation( repass_et, RegexTemplate.NOT_EMPTY," PLEASE FILL THIS FIELD");
        awesomeValidation.addValidation(email,RegexTemplate.NOT_EMPTY,"PLEASE FILL THIS FIELD");
        awesomeValidation.addValidation( address, RegexTemplate.NOT_EMPTY," PLEASE FILL THIS FIELD");
        awesomeValidation.addValidation(ailments, RegexTemplate.NOT_EMPTY,"Please fill this field");
        awesomeValidation.addValidation(age, RegexTemplate.NOT_EMPTY, "Please fill this field");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sname = name.getText().toString();
                spass = pass.getText().toString();
                sphno = phno.getText().toString();
                saddress = address.getText().toString();
                semail = email.getText().toString();
                sage = age.getText().toString();
                sailments = ailments.getText().toString();
                srepass = repass_et.getText().toString();
                int num_age = Integer.parseInt(sage);

                if(awesomeValidation.validate() && validateMobile(sphno) && validateEmail(semail) && validatePassword(spass) && !diabetes.isChecked() && srepass.equals(spass) && num_age>17
                && !diseases.isChecked() && !hemoglobin.isChecked()){
                    int b;
                    DBHelper helper = new DBHelper(getApplicationContext());
                    b = helper.insertBloodDonation(sname, sphno, choice_bld_grp, semail, saddress,  sailments, sgender, sage, spass);


                    if(b == 1){
                        Toast.makeText(DonationRegisterActivity.this, "Request made successfully!!", Toast.LENGTH_SHORT).show();


                        SmsManager manager = SmsManager.getDefault();
                        manager.sendTextMessage(sphno, null, "Your Registration for blood donation is made successfully!!",null, null);
                        /*

                        GMailSender sender = new GMailSender("khushishetty274@gmail.com","jayhar274");
                        String body = "You have successfully registered as a Donor!";
                        String subject = "Elixer Donor Registration";
                        String recipient =  semail;
                        try{
                            sender.sendMail(subject, body, "sweetcookie274@gmail.com",recipient);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(DonationRegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                         */


                    }
                    else if(b==2){
                        Toast.makeText(DonationRegisterActivity.this, "Request Failed!!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(DonationRegisterActivity.this, "The phone number is already registered!!", Toast.LENGTH_SHORT).show();
                    }
                }if(!validateMobile(sphno)){
                    phno.setError("Invalid phone number !!");
                }
                if(!validateEmail(semail)){
                    email.setError("Invalid email !!");
                }if(!validatePassword(spass)){
                    pass.setError("Weak Password!!");
                }if(!srepass.equals(spass)){
                    repass_et.setError("Password does not match");
                }
                if(num_age<=17 || diseases.isChecked() || hemoglobin.isChecked() || diabetes.isChecked()){
                    new AlertDialog.Builder(DonationRegisterActivity.this)
                            .setTitle("Blood Donation")
                            .setMessage("Sorry! Not eligible to donate")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();

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