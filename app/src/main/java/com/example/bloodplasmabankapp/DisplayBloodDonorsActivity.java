package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bloodplasmabankapp.Adapters.BloodDonorAdapter;
import com.example.bloodplasmabankapp.DB.DBHelper;
import com.example.bloodplasmabankapp.Models.BloodDonorModel;

import java.util.ArrayList;

public class DisplayBloodDonorsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_blood_donors);
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


    }
}