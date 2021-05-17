package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bloodplasmabankapp.Adapters.BloodDonorAdapter;
import com.example.bloodplasmabankapp.DB.DBHelper;
import com.example.bloodplasmabankapp.Models.BloodDonorModel;

import java.util.ArrayList;

public class DisplayBloodDonorsActivity extends AppCompatActivity {

    CardView btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    Button display_all;
    EditText et_filterCity;
    Button btn_filterCity;
    RecyclerView recyclerView;
    String bloodGrpSet = "",bloodGrpPlace="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_blood_donors);

        btn1 = (CardView) findViewById(R.id.a_positive_btn);
        btn2 = (CardView) findViewById(R.id.b_positive_btn);
        btn3 = (CardView) findViewById(R.id.o_positive_btn);
        btn4 = (CardView) findViewById(R.id.ab_positive_btn);
        btn5 = (CardView) findViewById(R.id.a_negative_btn);
        btn6 = (CardView) findViewById(R.id.b_negative_btn);
        btn7 = (CardView) findViewById(R.id.o_negative_btn);
        btn8 = (CardView) findViewById(R.id.ab_negative_btn);

        display_all = (Button) findViewById(R.id.all_b_donors_display);

        et_filterCity = (EditText) findViewById(R.id.filterByCityBlood);
        btn_filterCity = (Button) findViewById(R.id.filterByCityBloodBtn);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_id_2);

        /*
        ArrayList<BloodDonorModel>list = new ArrayList<>();
        list.add(new BloodDonorModel("Khushi", "O+ve","Bangalore"));
        list.add(new BloodDonorModel("Jaya","B-ve","Bangalore"));
         */

        DBHelper helper = new DBHelper(this);
         /*
        helper.insertOrder("Padma","9972920804","O-ve","padma@gmail.com","Uliya, Alangar, Marpady, Moodbidri","None","Female");
        helper.insertOrder("Varun","5128456335","O-ve","maithili@gmail.com","Udupi","None","Female");
        helper.insertOrder("Sara","6514788115","O+ve","jay@gmail.com","Kerala","None","Female");
        helper.insertOrder("Laura","9745215112","A-ve","annie@gmail.com","Mangalore","None","Female");
        helper.insertOrder("Maithili","9480016150","O+ve","maithili@gmail.com","Udupi","None","Female");
        helper.insertOrder("Ram","8105199132","AB+ve","khushi@gmail.com","Delhi","None","Male");
        helper.insertOrder("Sam","9886724699","B+ve","jay@gmail.com","Kerala","None","Female");
        helper.insertOrder("Annie","9745216852","A+ve","annie@gmail.com","Mangalore","None","Female");
        helper.insertOrder("Max","8106213221","O+ve","max@gmail.com","Bangalore","None","Male");
        helper.insertOrder("Jack","8456744412","A+ve","jack@gmail.com","Mumbai","None","Male");


         */
        ArrayList<BloodDonorModel> list = helper.getBloodDonors();

        BloodDonorAdapter adapter = new BloodDonorAdapter(list, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGrpSet = "A+ve";
                ArrayList<BloodDonorModel> lst;
                if(bloodGrpPlace.isEmpty()){
                    lst = helper.getBloodDonorsGroupWise("A+ve");
                    lst.addAll(helper.getBloodDonorsGroupWise("A-ve"));
                    lst.addAll(helper.getBloodDonorsGroupWise("O+ve"));
                    lst.addAll(helper.getBloodDonorsGroupWise("O-ve"));

                }
                else{
                    lst = helper.getBloodDonorByCity(bloodGrpPlace,"A+ve");
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"A-ve"));
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"O+ve"));
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"O-ve"));
                }
                if (lst.size() == 0) {
                    Toast.makeText(DisplayBloodDonorsActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }
                    BloodDonorAdapter adapter = new BloodDonorAdapter(lst, DisplayBloodDonorsActivity.this);
                    recyclerView.setAdapter(adapter);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGrpSet = "B+ve";
                ArrayList<BloodDonorModel> lst;
                if(bloodGrpPlace.isEmpty()){
                    lst = helper.getBloodDonorsGroupWise("B+ve");
                    lst.addAll(helper.getBloodDonorsGroupWise("B-ve"));
                    lst.addAll(helper.getBloodDonorsGroupWise("O+ve"));
                    lst.addAll(helper.getBloodDonorsGroupWise("O-ve"));

                }
                else{
                    lst = helper.getBloodDonorByCity(bloodGrpPlace,"B+ve");
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"B-ve"));
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"O+ve"));
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"O-ve"));
                }
                if (lst.size() == 0) {
                    Toast.makeText(DisplayBloodDonorsActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }
                    BloodDonorAdapter adapter = new BloodDonorAdapter(lst, DisplayBloodDonorsActivity.this);
                    recyclerView.setAdapter(adapter);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);


            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGrpSet = "O+ve";
                ArrayList<BloodDonorModel> lst;
                if(bloodGrpPlace.isEmpty()){
                    lst = helper.getBloodDonorsGroupWise("O+ve");
                    lst.addAll(helper.getBloodDonorsGroupWise("O-ve"));
                }
                else{
                    lst = helper.getBloodDonorByCity(bloodGrpPlace,"O+ve");
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"O-ve"));

                }
                if (lst.size() == 0) {
                    Toast.makeText(DisplayBloodDonorsActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }
                    BloodDonorAdapter adapter = new BloodDonorAdapter(lst, DisplayBloodDonorsActivity.this);
                    recyclerView.setAdapter(adapter);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGrpSet = "AB+ve";
                ArrayList<BloodDonorModel> lst;
                if(bloodGrpPlace.isEmpty()){
                    lst = helper.getBloodDonorsGroupWise("AB+ve");
                    lst.addAll(helper.getBloodDonorsGroupWise("A+ve"));
                    lst.addAll(helper.getBloodDonorsGroupWise("B+ve"));
                    lst.addAll(helper.getBloodDonorsGroupWise("O+ve"));
                    lst.addAll(helper.getBloodDonorsGroupWise("AB-ve"));
                    lst.addAll(helper.getBloodDonorsGroupWise("A-ve"));
                    lst.addAll(helper.getBloodDonorsGroupWise("B-ve"));
                    lst.addAll(helper.getBloodDonorsGroupWise("O-ve"));
                }
                else{
                    lst = helper.getBloodDonorByCity(bloodGrpPlace,"AB+ve");
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"A+ve"));
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"B+ve"));
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"O+ve"));
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"AB-ve"));
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"A-ve"));
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"B-ve"));
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"O-ve"));

                }
                if (lst.size() == 0) {
                    Toast.makeText(DisplayBloodDonorsActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }
                    BloodDonorAdapter adapter = new BloodDonorAdapter(lst, DisplayBloodDonorsActivity.this);
                    recyclerView.setAdapter(adapter);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGrpSet = "A-ve";
                ArrayList<BloodDonorModel> lst;
                if(bloodGrpPlace.isEmpty()){
                    lst = helper.getBloodDonorsGroupWise("A-ve");
                    lst.addAll(helper.getBloodDonorsGroupWise("O-ve"));
                }
                else{
                    lst = helper.getBloodDonorByCity(bloodGrpPlace,"A-ve");
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"O-ve"));
                }
                if (lst.size() == 0) {
                    Toast.makeText(DisplayBloodDonorsActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }
                BloodDonorAdapter adapter = new BloodDonorAdapter(lst, DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);

            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGrpSet = "B-ve";
                ArrayList<BloodDonorModel> lst;
                if(bloodGrpPlace.isEmpty()){
                    lst = helper.getBloodDonorsGroupWise("B-ve");
                    lst.addAll(helper.getBloodDonorsGroupWise("O-ve"));
                }
                else{
                    lst = helper.getBloodDonorByCity(bloodGrpPlace,"B-ve");
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"O-ve"));

                }
                if (lst.size() == 0) {
                    Toast.makeText(DisplayBloodDonorsActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }
                BloodDonorAdapter adapter = new BloodDonorAdapter(lst, DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);

            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGrpSet = "O-ve";
                ArrayList<BloodDonorModel> lst;
                if(bloodGrpPlace.isEmpty()){
                    lst = helper.getBloodDonorsGroupWise("O-ve");

                }
                else{
                    lst = helper.getBloodDonorByCity(bloodGrpPlace,"O-ve");
                }
                if (lst.size() == 0) {
                    Toast.makeText(DisplayBloodDonorsActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }
                BloodDonorAdapter adapter = new BloodDonorAdapter(lst, DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);

            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGrpSet = "AB-ve";
                ArrayList<BloodDonorModel> lst;
                if(bloodGrpPlace.isEmpty()){
                    lst = helper.getBloodDonorsGroupWise("AB-ve");
                    lst.addAll(helper.getBloodDonorsGroupWise("A-ve"));
                    lst.addAll(helper.getBloodDonorsGroupWise("B-ve"));
                    lst.addAll(helper.getBloodDonorsGroupWise("O-ve"));
                }
                else{
                    lst = helper.getBloodDonorByCity(bloodGrpPlace,"AB-ve");
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"A-ve"));
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"B-ve"));
                    lst.addAll(helper.getBloodDonorByCity(bloodGrpPlace,"O-ve"));

                }
                if (lst.size() == 0) {
                    Toast.makeText(DisplayBloodDonorsActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }
                BloodDonorAdapter adapter = new BloodDonorAdapter(lst, DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);

            }
        });

        display_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGrpSet = "";
                bloodGrpPlace = "";
                ArrayList<BloodDonorModel> lst = helper.getBloodDonors();

                if (lst.size() == 0) {
                    Toast.makeText(DisplayBloodDonorsActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }
                et_filterCity.setText("");
                BloodDonorAdapter adapter = new BloodDonorAdapter(lst, DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);


            }
        });

        btn_filterCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = et_filterCity.getText().toString().toLowerCase();
                bloodGrpPlace = city;
                ArrayList<BloodDonorModel> lst;
                if(bloodGrpSet.isEmpty()){
                    lst = helper.getBloodDonorByCity(city);
                }
                else{
                    switch (bloodGrpSet){
                        case "A+ve":
                            lst = helper.getBloodDonorByCity(city,"A+ve");
                            lst.addAll(helper.getBloodDonorByCity(city,"A-ve"));
                            lst.addAll(helper.getBloodDonorByCity(city,"O+ve"));
                            lst.addAll(helper.getBloodDonorByCity(city,"O-ve"));
                            break;
                        case "B+ve":
                            lst = helper.getBloodDonorByCity(city,"B+ve");
                            lst.addAll(helper.getBloodDonorByCity(city,"B-ve"));
                            lst.addAll(helper.getBloodDonorByCity(city,"O+ve"));
                            lst.addAll(helper.getBloodDonorByCity(city,"O-ve"));
                            break;
                        case "O+ve":

                            lst = helper.getBloodDonorByCity(city,"O+ve");
                            lst.addAll(helper.getBloodDonorByCity(city,"O-ve"));
                            break;
                        case "AB+ve":
                            lst = helper.getBloodDonorByCity(city,"AB+ve");
                            lst.addAll(helper.getBloodDonorByCity(city,"A+ve"));
                            lst.addAll(helper.getBloodDonorByCity(city,"B+ve"));
                            lst.addAll(helper.getBloodDonorByCity(city,"O+ve"));
                            lst.addAll(helper.getBloodDonorByCity(city,"AB-ve"));
                            lst.addAll(helper.getBloodDonorByCity(city,"A-ve"));
                            lst.addAll(helper.getBloodDonorByCity(city,"B-ve"));
                            lst.addAll(helper.getBloodDonorByCity(city,"O-ve"));
                            break;
                        case "A-ve":
                            lst = helper.getBloodDonorByCity(city,"A-ve");
                            lst.addAll(helper.getBloodDonorByCity(city,"O-ve"));
                            break;
                        case "B-ve":
                            lst = helper.getBloodDonorByCity(city,"B-ve");
                            lst.addAll(helper.getBloodDonorByCity(city,"O-ve"));
                            break;
                        case "O-ve":
                            lst = helper.getBloodDonorByCity(city,"O-ve");
                            break;
                        case "AB-ve":
                            lst = helper.getBloodDonorByCity(city,"AB-ve");
                            lst.addAll(helper.getBloodDonorByCity(city,"A-ve"));
                            lst.addAll(helper.getBloodDonorByCity(city,"B-ve"));
                            lst.addAll(helper.getBloodDonorByCity(city,"O-ve"));
                            break;
                        default:
                            lst = helper.getBloodDonors();
                    }
                }

                if (lst.size() == 0) {
                    Toast.makeText(DisplayBloodDonorsActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }

                BloodDonorAdapter adapter = new BloodDonorAdapter(lst, DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);

            }

        });
    }
}