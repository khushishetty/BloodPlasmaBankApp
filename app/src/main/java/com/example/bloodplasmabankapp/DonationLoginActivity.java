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
import com.example.bloodplasmabankapp.DB.DBHelper;
import com.example.bloodplasmabankapp.DB.DB_Plasma_Helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DonationLoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    EditText ph, password;
    String choice, phno, pass;
    Button btn;

    AwesomeValidation validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_login);

        ph = (EditText)findViewById(R.id.donate_login_phno);
        password = (EditText)findViewById(R.id.donate_login_password);
        btn = (Button)findViewById(R.id.donation_login_btn);

        validation = new AwesomeValidation(ValidationStyle.BASIC);
        validation.addValidation(ph, RegexTemplate.NOT_EMPTY, "PLEASE FILL THIS FIELD!!");
        validation.addValidation(password, RegexTemplate.NOT_EMPTY, "PLEASE FILL THIS FIELD!!");

        spinner = (Spinner)findViewById(R.id.donation_typespinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.type_option, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phno = ph.getText().toString();
                pass = password.getText().toString();

                if(validation.validate() && validateMobile(phno)){
                    if(choice.equals("Blood") || choice.equals("Blood")){
                        DBHelper helper = new DBHelper(getApplicationContext());
                        int k = helper.getBloodDonationStatusForLogin(phno, pass);
                        if (k == 1) {
                            Intent intent = new Intent(DonationLoginActivity.this, DonationModifyActivity.class);
                            intent.putExtra("phno", phno);
                            intent.putExtra("type", choice);
                            startActivity(intent);
                        }
                        if (k == 2) {
                            Toast.makeText(DonationLoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                        if (k == 3) {
                            Toast.makeText(DonationLoginActivity.this, "No entries found", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{

                        DB_Plasma_Helper helper = new DB_Plasma_Helper(getApplicationContext());
                        int k = helper.getPlasmaDonationStatusForLogin(phno, pass);
                        if (k == 1) {
                            Intent intent = new Intent(DonationLoginActivity.this, DonationModifyActivity.class);
                            intent.putExtra("phno", phno);
                            intent.putExtra("type", choice);
                            startActivity(intent);
                        }
                        if (k == 2) {
                            Toast.makeText(DonationLoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                        if (k == 3) {
                            Toast.makeText(DonationLoginActivity.this, "This number is not registered!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else if(! validateMobile(phno)){
                    ph.setError("Invalid Phone number!!");
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
        choice = "Blood";
    }

    boolean validateMobile(String input){
        Pattern p = Pattern.compile("[6-9][0-9]{9}");
        Matcher m = p.matcher(input);
        return m.matches();
    }

}

