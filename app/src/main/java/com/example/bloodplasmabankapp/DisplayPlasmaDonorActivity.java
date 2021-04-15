package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bloodplasmabankapp.Adapters.PlasmaDonorAdapter;
import com.example.bloodplasmabankapp.DB.DB_Plasma_Helper;
import com.example.bloodplasmabankapp.Models.PlasmaDonorModel;

import java.util.ArrayList;

public class DisplayPlasmaDonorActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_plasma_donor);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_id_2);

        //ArrayList<PlasmaDonorModel> list = new ArrayList<>();
        //list.add(new PlasmaDonorModel("Khushi","Ove","Bangalore"));

        DB_Plasma_Helper helper = new DB_Plasma_Helper(this);
        /*
        helper.insertPlasmaDonor("Khushi","Bangalore");
        helper.insertPlasmaDonor("Maithili","Udupi");
        helper.insertPlasmaDonor("Rita","Mangalore");
         */
        ArrayList<PlasmaDonorModel> list = helper.getPlasmaDonors();

        PlasmaDonorAdapter adapter = new PlasmaDonorAdapter(list,this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}