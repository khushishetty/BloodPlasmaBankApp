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
import android.widget.Toast;

import com.example.bloodplasmabankapp.Adapters.BloodDonorAdapter;
import com.example.bloodplasmabankapp.DB.DBHelper;
import com.example.bloodplasmabankapp.Models.BloodDonorModel;

import java.util.ArrayList;

public class DisplayBloodDonorsActivity extends AppCompatActivity {

    CardView btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    Button display_all;
    RecyclerView recyclerView;
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

        display_all = (Button)findViewById(R.id.all_b_donors_display);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview_id_2);

        /*
        ArrayList<BloodDonorModel>list = new ArrayList<>();
        list.add(new BloodDonorModel("Khushi", "O+ve","Bangalore"));
        list.add(new BloodDonorModel("Jaya","B-ve","Bangalore"));
         */

        DBHelper helper = new DBHelper(this);
        /*
        helper.deleteOrder("Maithili");
        helper.deleteOrder("Annie");
        helper.deleteOrder("Max");
        helper.deleteOrder("Jack");
        helper.insertOrder("Maithili","9480016150","O+ve","maithili@gmail.com","Udupi","None","Female");
        helper.insertOrder("Ram","8105199132","AB+ve","khushi@gmail.com","Delhi","None","Male");
        helper.insertOrder("Sam","9886724699","B+ve","jay@gmail.com","Kerala","None","Female");
        helper.insertOrder("Annie","9745216852","A+ve","annie@gmail.com","Mangalore","None","Female");
        helper.insertOrder("Max","8106213221","O+ve","max@gmail.com","Bangalore","None","Male");
        helper.insertOrder("Jack","8456744412","A+ve","jack@gmail.com","Mumbai","None","Male");


         */
        ArrayList<BloodDonorModel> list = helper.getBloodDonors();

        BloodDonorAdapter adapter = new BloodDonorAdapter(list,this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BloodDonorModel> lst = helper.getBloodDonorsGroupWise("A+ve");

                BloodDonorAdapter adapter = new BloodDonorAdapter(lst,DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BloodDonorModel> lst = helper.getBloodDonorsGroupWise("B+ve");

                BloodDonorAdapter adapter = new BloodDonorAdapter(lst,DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BloodDonorModel> lst = helper.getBloodDonorsGroupWise("O+ve");

                BloodDonorAdapter adapter = new BloodDonorAdapter(lst,DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BloodDonorModel> lst = helper.getBloodDonorsGroupWise("AB+ve");

                BloodDonorAdapter adapter = new BloodDonorAdapter(lst,DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BloodDonorModel> lst = helper.getBloodDonorsGroupWise("A-ve");

                BloodDonorAdapter adapter = new BloodDonorAdapter(lst,DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BloodDonorModel> lst = helper.getBloodDonorsGroupWise("B-ve");

                BloodDonorAdapter adapter = new BloodDonorAdapter(lst,DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BloodDonorModel> lst = helper.getBloodDonorsGroupWise("O-ve");

                BloodDonorAdapter adapter = new BloodDonorAdapter(lst,DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BloodDonorModel> lst = helper.getBloodDonorsGroupWise("AB-ve");

                BloodDonorAdapter adapter = new BloodDonorAdapter(lst,DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

        display_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BloodDonorModel> lst = helper.getBloodDonors();

                BloodDonorAdapter adapter = new BloodDonorAdapter(lst,DisplayBloodDonorsActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayBloodDonorsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);

            }
        });
    }
}