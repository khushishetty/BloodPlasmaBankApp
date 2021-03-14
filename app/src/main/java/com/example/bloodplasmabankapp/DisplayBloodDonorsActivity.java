package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bloodplasmabankapp.Adapters.BloodDonorAdapter;
import com.example.bloodplasmabankapp.Models.BloodDonorModel;

import java.util.ArrayList;

public class DisplayBloodDonorsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_blood_donors);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview_id_1);


        /*
        ArrayList<BloodDonorModel>list = new ArrayList<>();
        list.add(new BloodDonorModel("Khushi", "O+ve","Bangalore"));
        list.add(new BloodDonorModel("Jaya","B-ve","Bangalore"));
         */

        DBHelper helper = new DBHelper(this);
        /*
        helper.insertOrder("Khushi","9236473283","O+ve","jay@gmail.com","Bangalore");
        helper.insertOrder("Jaya","9886724699","O+ve","jay@gmail.com","Bangalore");
        helper.insertOrder("Khushi","8105199132","O+ve","jay@gmail.com","Bangalore");
        helper.insertOrder("Ajja","9972920804","O+ve","jay@gmail.com","Bangalore");
        helper.insertOrder("Mamma","9886724699","O+ve","jay@gmail.com","Bangalore");
         */
        ArrayList<BloodDonorModel> list = helper.getBloodDonors();

        BloodDonorAdapter adapter = new BloodDonorAdapter(list,this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


    }
}