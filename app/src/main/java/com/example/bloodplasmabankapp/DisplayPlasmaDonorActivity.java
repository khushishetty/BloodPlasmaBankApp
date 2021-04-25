package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.bloodplasmabankapp.Adapters.PlasmaDonorAdapter;
import com.example.bloodplasmabankapp.DB.DB_Plasma_Helper;
import com.example.bloodplasmabankapp.Models.PlasmaDonorModel;

import java.util.ArrayList;

public class DisplayPlasmaDonorActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    EditText searchCity;
    Button searchCityBtn,undoSearchBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_plasma_donor);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_id_2);
        searchCity = (EditText) findViewById(R.id.filterByCityPlasma);
        searchCityBtn = (Button)findViewById(R.id.filterByCityPlasmaBtn);
        undoSearchBtn = (Button)findViewById(R.id.defilterCityBtn);

        //ArrayList<PlasmaDonorModel> list = new ArrayList<>();
        //list.add(new PlasmaDonorModel("Khushi","Ove","Bangalore"));

        DB_Plasma_Helper helper = new DB_Plasma_Helper(this);
        /*
        helper.deletePlasmaDonor("Saritha");
        helper.deletePlasmaDonor("Raghav");
        helper.deletePlasmaDonor("Neeta");
        helper.deletePlasmaDonor("Rose");
        helper.deletePlasmaDonor("Vinay");
        helper.deletePlasmaDonor("Jill");
        helper.insertPlasmaDonor("Saritha","9480016150","O+ve","saritha@gmail.com","Udupi","None","Female");
        helper.insertPlasmaDonor("Raghav","8105199132","AB+ve","raghav@gmail.com","Delhi","Diabities","Male");
        helper.insertPlasmaDonor("Neeta","9886724699","B-ve","neeta@gmail.com","Kerala","None","Female");
        helper.insertPlasmaDonor("Rose","9745216852","A+ve","rose@gmail.com","Mangalore","Renal Problems","Female");
        helper.insertPlasmaDonor("Vinay","8106213221","O+ve","vinay@gmail.com","Bangalore","None","Male");
        helper.insertPlasmaDonor("Jill","8456744412","A-ve","jill@gmail.com","Mumbai","None","Male");

         */
        ArrayList<PlasmaDonorModel> list = helper.getPlasmaDonors();

        PlasmaDonorAdapter adapter = new PlasmaDonorAdapter(list,this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        searchCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = searchCity.getText().toString().toLowerCase();
                if(city.equals("")){
                    Toast.makeText(DisplayPlasmaDonorActivity.this, "Enter a city", Toast.LENGTH_LONG).show();
                }
                else{
                    ArrayList<PlasmaDonorModel> list = helper.getPlasmaDonorByCity(city);
                    if(list.size() == 0){
                        Toast.makeText(DisplayPlasmaDonorActivity.this, "No donors found", Toast.LENGTH_SHORT).show();
                    }

                    PlasmaDonorAdapter adapter = new PlasmaDonorAdapter(list,DisplayPlasmaDonorActivity.this);
                    recyclerView.setAdapter(adapter);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayPlasmaDonorActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    Toast.makeText(DisplayPlasmaDonorActivity.this, list.size() + " donor(s) found", Toast.LENGTH_SHORT).show();

                }

            }
        });
        undoSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<PlasmaDonorModel> list = helper.getPlasmaDonors();

                PlasmaDonorAdapter adapter = new PlasmaDonorAdapter(list,DisplayPlasmaDonorActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayPlasmaDonorActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);

                Toast.makeText(DisplayPlasmaDonorActivity.this, list.size() + " donor(s) found", Toast.LENGTH_SHORT).show();
            }
        });

    }

}