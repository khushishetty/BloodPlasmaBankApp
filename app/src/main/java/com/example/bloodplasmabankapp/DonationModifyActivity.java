package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DonationModifyActivity extends AppCompatActivity {

    Spinner spinner, gender;
    String choice_bld_grp,choice_type, sname, sphno, semail, saddress, spass, sage, sailments, sgender;
    EditText name, pass, phno, email, address, age, ailments;
    Button  submit, delete, logout;
    AwesomeValidation awesomeValidation;
    int id;


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
        setContentView(R.layout.activity_donation_modify);

        DB_Plasma_Helper phelper = new DB_Plasma_Helper(getApplicationContext());
        DBHelper bhelper = new DBHelper(getApplicationContext());

        Intent intent = getIntent();

        sphno = intent.getStringExtra("phno");
        choice_type = intent.getStringExtra("type");

        Cursor cursor;
        if(choice_type.equals("Blood") || choice_type.equals("Blood")){
            cursor = bhelper.getBloodDonorByPhone(sphno);
        }
        else{
            cursor = phelper.getPlasmaDonorByPhone(sphno);
        }
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        name = (EditText)findViewById(R.id.donate_modify_name_id);
        pass = (EditText)findViewById(R.id.donate_modify_pass_id);
        phno = (EditText)findViewById(R.id.donate_modify_phno_id);
        email = (EditText)findViewById(R.id.donate_modify_email_id);
        address = (EditText)findViewById(R.id.donate_modify_address_id);
        age = (EditText)findViewById(R.id.donate_modify_age_id);
        submit = (Button)findViewById(R.id.donate_modify_btn);
        ailments = (EditText)findViewById(R.id.donate_modify_ailments_id);
        spinner = (Spinner)findViewById(R.id.donate_modify_bloodgrp_id);
        logout = (Button)findViewById(R.id.donate_logout_btn);
        gender = (Spinner)findViewById(R.id.donate_modify_gender_id);
        delete = (Button)findViewById(R.id.donate_delete_btn);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.blood_grp_option, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter1);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.gender_option, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gender.setAdapter(adapter2);

        awesomeValidation.addValidation(name, RegexTemplate.NOT_EMPTY,"Please fill this field");
        awesomeValidation.addValidation(pass, RegexTemplate.NOT_EMPTY,"Please fill this field");
        awesomeValidation.addValidation(phno, RegexTemplate.NOT_EMPTY,"Please fill this field");
        awesomeValidation.addValidation(email, RegexTemplate.NOT_EMPTY,"Please fill this field");
        awesomeValidation.addValidation(address, RegexTemplate.NOT_EMPTY,"Please fill this field");
        awesomeValidation.addValidation(ailments, RegexTemplate.NOT_EMPTY,"Please fill this field");
        awesomeValidation.addValidation(age, RegexTemplate.NOT_EMPTY, "Please fill this field");


        name.setText(cursor.getString(2));
        pass.setText(cursor.getString(9));
        phno.setText(cursor.getString(1));
        email.setText(cursor.getString(4));
        address.setText(cursor.getString(5));
        age.setText(cursor.getString(8));
        ailments.setText(cursor.getString(6));
        id = cursor.getInt(0);

        spinner.setSelection(adapter1.getPosition(cursor.getString(3)));
        gender.setSelection(adapter2.getPosition(cursor.getString(7)));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_bld_grp = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                choice_bld_grp = cursor.getString(4);
            }
        });

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sgender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                choice_type = cursor.getString(8);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DonationModifyActivity.this)
                        .setTitle("Update Details")
                        .setMessage("Are you sure you want to update your details?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sname = name.getText().toString();
                                spass = pass.getText().toString();
                                sphno = phno.getText().toString();
                                saddress = address.getText().toString();
                                semail = email.getText().toString();
                                sage = age.getText().toString();
                                sailments = ailments.getText().toString();

                                if(awesomeValidation.validate() && validateMobile(sphno) && validateEmail(semail) && validatePassword(spass)){
                                    boolean b;
                                    if(choice_type.equals("Blood")){
                                        DBHelper helper = new DBHelper(getApplicationContext());
                                        b = helper.updateBloodDonor(sname, sphno, choice_bld_grp, semail, saddress,  sailments, sgender, sage, spass,id);
                                    }else{
                                        DB_Plasma_Helper helper2 = new DB_Plasma_Helper(getApplicationContext());
                                        b = helper2.updatePlasmaDonor(sname, sphno, choice_bld_grp, semail, saddress,  sailments, sgender, sage, spass,id);
                                    }

                                    if(b){
                                        Toast.makeText(DonationModifyActivity.this, "Update made successfully!!", Toast.LENGTH_SHORT).show();

                                        /*
                                        SmsManager manager = SmsManager.getDefault();
                                        manager.sendTextMessage(sphno, null, "Your request has been successfully made!",null, null);

                                         */
                                        SmsManager manager = SmsManager.getDefault();
                                        manager.sendTextMessage(sphno, null, "Your details for "+choice_type+" donation have been updated successfully!!",null, null);
                                    }
                                    else{
                                        Toast.makeText(DonationModifyActivity.this, "Update has failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }if(!validateMobile(sphno)){
                                    phno.setError("Invalid phone number !!");
                                }
                                if(!validateEmail(semail)){
                                    email.setError("Invalid email !!");
                                }if(!validatePassword(spass)){
                                    pass.setError("Weak Password!!");
                                }


                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DonationModifyActivity.this)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int b;
                                if(choice_type.equals("Blood")){
                                    DBHelper helper = new DBHelper(getApplicationContext());
                                    b = helper.deleteBloodDonor(id);
                                }else{
                                    DB_Plasma_Helper helper2 = new DB_Plasma_Helper(getApplicationContext());
                                    b = helper2.deletePlasmaDonor(id);
                                }
                                if(b>0){
                                    SmsManager manager = SmsManager.getDefault();
                                    manager.sendTextMessage(phno.getText().toString(), null, "Your account has been deleted succesfully!!",null, null);
                                    Toast.makeText(DonationModifyActivity.this, "Deletion Successfull!!", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(DonationModifyActivity.this, MainActivity.class);
                                    startActivity(intent1);
                                }
                                else{
                                    Toast.makeText(DonationModifyActivity.this, "ERROR!!", Toast.LENGTH_SHORT).show();

                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DonationModifyActivity.this)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to log out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(DonationModifyActivity.this, MainActivity.class));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
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