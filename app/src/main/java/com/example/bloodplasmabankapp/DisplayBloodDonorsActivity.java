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

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_blood_donors);

        btn1 = (CardView) findViewById(R.id.a_positive_btn);
        btn2 = (CardView) findViewById(R.id.b_positive_btn);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview_id_2);

        /*
        ArrayList<BloodDonorModel>list = new ArrayList<>();
        list.add(new BloodDonorModel("Khushi", "O+ve","Bangalore"));
        list.add(new BloodDonorModel("Jaya","B-ve","Bangalore"));
         */

        DBHelper helper = new DBHelper(this);
        /*
        helper.insertOrder("Khushi","8105199132","O+ve","khushi@gmail.com","Bangalore","None","Female");
        helper.insertOrder("Rita","9886724699","B+ve","jay@gmail.com","Mangalore","None","Female");
         */
        ArrayList<BloodDonorModel> list = helper.getBloodDonors();

        BloodDonorAdapter adapter = new BloodDonorAdapter(list,this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BloodDonorModel> lst = helper.getBloodDonorsGroupWise("O+ve");

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
    }
}