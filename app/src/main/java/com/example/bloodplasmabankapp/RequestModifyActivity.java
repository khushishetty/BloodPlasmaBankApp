package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestModifyActivity extends AppCompatActivity {

    Spinner spinner,spinner_type;
    String choice_bld_grp,choice_type, sname, sphno, semail, saddress, surgency, spass, sage;
    int id;
    EditText name, pass, phno, email, address,age;
    CheckBox urgency;
    Button delete, update, logout;

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
        setContentView(R.layout.activity_request_modify);

        Db_Helper_Requests helper = new Db_Helper_Requests(getApplicationContext());
        Intent intent  =getIntent();

        sphno = intent.getStringExtra("phno");
        choice_type = intent.getStringExtra("type");

        Cursor cursor = helper.getRequestsByPhoneAndType(sphno,choice_type);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        name = (EditText)findViewById(R.id.req_modify_name_id);
        pass = (EditText)findViewById(R.id.req_modify_pass_id);
        phno = (EditText)findViewById(R.id.req_modify_phno_id);
        email = (EditText)findViewById(R.id.req_modify_email_id);
        address = (EditText)findViewById(R.id.req_modify_address_id);
        age = (EditText)findViewById(R.id.req_modify_age_id);
        urgency = (CheckBox)findViewById(R.id.req_modify_urgent_id);
        update = (Button) findViewById(R.id.req_modify_btn);
        delete = (Button)findViewById(R.id.reg_delete_btn);
        logout = (Button)findViewById(R.id.request_logout_btn);
        spinner = (Spinner)findViewById(R.id.req_modify_bloodgrp_id);
        spinner_type = (Spinner)findViewById(R.id.spinner_modify_type_id);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.blood_grp_option, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.type_option, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_type.setAdapter(adapter);

        awesomeValidation.addValidation(name, RegexTemplate.NOT_EMPTY,"Please fill this field");
        awesomeValidation.addValidation(pass, RegexTemplate.NOT_EMPTY,"Please fill this field");
        awesomeValidation.addValidation(phno, RegexTemplate.NOT_EMPTY,"Please fill this field");
        awesomeValidation.addValidation(email, RegexTemplate.NOT_EMPTY,"Please fill this field");
        awesomeValidation.addValidation(address, RegexTemplate.NOT_EMPTY,"Please fill this field");

        /*
        values.put("phoneno", phno);
        values.put("name", name);
        values.put("bloodgroup", bgp);
        values.put("emailaddress", email);
        values.put("address", address);
        values.put("b_or_p", borp);
        values.put("urgent", urgent);
        values.put("password",pass);
         */
        //Set values
        name.setText(cursor.getString(2));
        email.setText(cursor.getString(4));
        address.setText(cursor.getString(5));
        if(cursor.getString(7).equals("Yes"))
            urgency.setChecked(true);
        pass.setText(cursor.getString(8));
        phno.setText(sphno);
        spinner.setSelection(adapter1.getPosition(cursor.getString(3)));
        spinner_type.setSelection(adapter.getPosition(choice_type));
        id = cursor.getInt(0);
        age.setText(cursor.getString(10));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(RequestModifyActivity.this)
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
                                if(urgency.isChecked()){
                                    surgency = "Yes";
                                }
                                else{
                                    surgency = "No";
                                }
                                if(awesomeValidation.validate() && validateMobile(sphno) && validateEmail(semail) && validatePassword(spass)){
                                    Db_Helper_Requests helper = new Db_Helper_Requests(getApplicationContext());
                                    boolean b = helper.updateRequests(sname, sphno, choice_bld_grp, semail, saddress, choice_type, surgency, spass,id,sage);
                                    if(b){
                                        Toast.makeText(RequestModifyActivity.this, "Update Successful!!", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(RequestModifyActivity.this, "Sorry!! Couldn't update.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                if(!validateMobile(sphno)){
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

                new AlertDialog.Builder(RequestModifyActivity.this)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Db_Helper_Requests helper = new Db_Helper_Requests(getApplicationContext());
                                int i = helper.deleteRequest(id);
                                if(i>0){
                                    Toast.makeText(RequestModifyActivity.this, "Deletion Successfull!!", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(RequestModifyActivity.this, MainActivity.class);
                                    startActivity(intent1);
                                }
                                else{
                                    Toast.makeText(RequestModifyActivity.this, "ERROR!!", Toast.LENGTH_SHORT).show();
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
                new AlertDialog.Builder(RequestModifyActivity.this)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to log out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(RequestModifyActivity.this, MainActivity.class));
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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_bld_grp = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                choice_bld_grp = cursor.getString(3);
            }
        });

        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_type = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                choice_type = intent.getStringExtra("type");

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