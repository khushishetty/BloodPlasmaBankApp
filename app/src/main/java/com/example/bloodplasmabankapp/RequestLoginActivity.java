package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.bloodplasmabankapp.DB.Db_Helper_Requests;

import kotlin.text.Regex;

public class RequestLoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    EditText ph, password;
    String choice, phno, pass;
    Button btn;

    AwesomeValidation validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_login);



        ph = (EditText)findViewById(R.id.req_login_phno);
        password = (EditText)findViewById(R.id.req_login_password);
        btn = (Button)findViewById(R.id.req_login_btn);


        validation = new AwesomeValidation(ValidationStyle.BASIC);
        validation.addValidation(ph, RegexTemplate.NOT_EMPTY, "PLEASE FILL THIS FIELD!!");
        validation.addValidation(password, RegexTemplate.NOT_EMPTY, "PLEASE FILL THIS FIELD!!");

        spinner = (Spinner)findViewById(R.id.typespinner);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this,R.array.type_option, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phno = ph.getText().toString();
                pass = password.getText().toString();

                if(validation.validate()) {
                    Db_Helper_Requests helperRequests = new Db_Helper_Requests(getApplicationContext());
                    int k = helperRequests.getBloodRequestStatusForLogin(phno, pass, choice);
                    if (k == 1) {
                        Intent intent = new Intent(RequestLoginActivity.this, RequestModifyActivity.class);
                        intent.putExtra("phno", phno);
                        intent.putExtra("type", choice);
                        startActivity(intent);
                    }
                    if (k == 2) {
                        Toast.makeText(RequestLoginActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    }
                    if (k == 3) {
                        Toast.makeText(RequestLoginActivity.this, "No entries found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        choice = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        choice = "blood";
    }
}