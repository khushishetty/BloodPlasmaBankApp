package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.bloodplasmabankapp.Adapters.BloodDonorAdapter;
import com.example.bloodplasmabankapp.Adapters.PlasmaDonorAdapter;
import com.example.bloodplasmabankapp.DB.DB_Plasma_Helper;
import com.example.bloodplasmabankapp.Models.BloodDonorModel;
import com.example.bloodplasmabankapp.Models.PlasmaDonorModel;

import java.util.ArrayList;

public class DisplayPlasmaDonorActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    EditText searchCity;
    Button searchCityBtn,undoSearchBtn;
    CardView btn1, btn2, btn3, btn4;
    String bloodGrpSet = "",bloodGrpPlace="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_plasma_donor);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_id_2);
        searchCity = (EditText) findViewById(R.id.filterByCityPlasma);
        searchCityBtn = (Button)findViewById(R.id.filterByCityPlasmaBtn);
        undoSearchBtn = (Button)findViewById(R.id.defilterCityBtn);

        btn1 = (CardView) findViewById(R.id.a_positive_btn_plasma);
        btn2 = (CardView) findViewById(R.id.b_positive_btn_plasma);
        btn3 = (CardView) findViewById(R.id.o_positive_btn_plasma);
        btn4 = (CardView) findViewById(R.id.ab_positive_btn_plasma);


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

        helper.insertPlasmaDonor("Saritha","9480016150","O+ve","saritha@gmail.com","Udupi","None","Female","22");
        helper.insertPlasmaDonor("Raghav","8105199132","AB+ve","raghav@gmail.com","Delhi","Diabities","Male","35");
        helper.insertPlasmaDonor("Neeta","9886724699","B-ve","neeta@gmail.com","Kerala","None","Female","44");
        helper.insertPlasmaDonor("Rose","9745216852","A+ve","rose@gmail.com","Mangalore","Renal Problems","Female","51");
        helper.insertPlasmaDonor("Vinay","8106213221","O+ve","vinay@gmail.com","Bangalore","None","Male","47");
        helper.insertPlasmaDonor("Jill","8456744412","A-ve","jill@gmail.com","Mumbai","None","Male","36");


         */
        ArrayList<PlasmaDonorModel> list = helper.getPlasmaDonors();

        PlasmaDonorAdapter adapter = new PlasmaDonorAdapter(list,this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGrpSet = "A+ve";
                ArrayList<PlasmaDonorModel> lst;
                if(bloodGrpPlace.isEmpty()){
                    lst = helper.getPlasmaDonorsGroupwise("A+ve");
                    lst.addAll(helper.getPlasmaDonorsGroupwise("A-ve"));
                    lst.addAll(helper.getPlasmaDonorsGroupwise("AB+ve"));
                    lst.addAll(helper.getPlasmaDonorsGroupwise("AB-ve"));

                }
                else{
                    lst = helper.getPlasmaDonorByCity(bloodGrpPlace,"A+ve");
                    lst.addAll(helper.getPlasmaDonorByCity(bloodGrpPlace,"A-ve"));
                    lst.addAll(helper.getPlasmaDonorByCity(bloodGrpPlace,"AB+ve"));
                    lst.addAll(helper.getPlasmaDonorByCity(bloodGrpPlace,"AB-ve"));
                }
                if (lst.size() == 0) {
                    Toast.makeText(DisplayPlasmaDonorActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DisplayPlasmaDonorActivity.this, lst.size()+" donors found!", Toast.LENGTH_SHORT).show();
                }
                PlasmaDonorAdapter adapter = new PlasmaDonorAdapter(lst,DisplayPlasmaDonorActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayPlasmaDonorActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGrpSet = "B+ve";
                ArrayList<PlasmaDonorModel> lst;
                if(bloodGrpPlace.isEmpty()){
                    lst = helper.getPlasmaDonorsGroupwise("B+ve");
                    lst.addAll(helper.getPlasmaDonorsGroupwise("B-ve"));
                    lst.addAll(helper.getPlasmaDonorsGroupwise("AB+ve"));
                    lst.addAll(helper.getPlasmaDonorsGroupwise("AB-ve"));

                }
                else{
                    lst = helper.getPlasmaDonorByCity(bloodGrpPlace,"B+ve");
                    lst.addAll(helper.getPlasmaDonorByCity(bloodGrpPlace,"B-ve"));
                    lst.addAll(helper.getPlasmaDonorByCity(bloodGrpPlace,"AB+ve"));
                    lst.addAll(helper.getPlasmaDonorByCity(bloodGrpPlace,"AB-ve"));
                }
                if (lst.size() == 0) {
                    Toast.makeText(DisplayPlasmaDonorActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DisplayPlasmaDonorActivity.this, lst.size()+" donors found!", Toast.LENGTH_SHORT).show();
                }
                PlasmaDonorAdapter adapter = new PlasmaDonorAdapter(lst, DisplayPlasmaDonorActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayPlasmaDonorActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);


            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGrpSet = "AB+ve";
                ArrayList<PlasmaDonorModel> lst;
                if(bloodGrpPlace.isEmpty()){
                    lst = helper.getPlasmaDonorsGroupwise("AB+ve");
                    lst.addAll(helper.getPlasmaDonorsGroupwise("AB-ve"));
                }
                else{
                    lst = helper.getPlasmaDonorByCity(bloodGrpPlace,"AB+ve");
                    lst.addAll(helper.getPlasmaDonorByCity(bloodGrpPlace,"AB-ve"));

                }
                if (lst.size() == 0) {
                    Toast.makeText(DisplayPlasmaDonorActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DisplayPlasmaDonorActivity.this, lst.size()+" donors found!", Toast.LENGTH_SHORT).show();
                }
                PlasmaDonorAdapter adapter = new PlasmaDonorAdapter(lst, DisplayPlasmaDonorActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayPlasmaDonorActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGrpSet = "O+ve";
                ArrayList<PlasmaDonorModel> lst;
                if(bloodGrpPlace.isEmpty()){
                    lst = helper.getPlasmaDonorsGroupwise("O+ve");
                    lst.addAll(helper.getPlasmaDonorsGroupwise("O-ve"));
                    lst.addAll(helper.getPlasmaDonorsGroupwise("AB+ve"));
                    lst.addAll(helper.getPlasmaDonorsGroupwise("AB-ve"));
                    lst.addAll(helper.getPlasmaDonorsGroupwise("A+ve"));
                    lst.addAll(helper.getPlasmaDonorsGroupwise("A-ve"));
                    lst.addAll(helper.getPlasmaDonorsGroupwise("B+ve"));
                    lst.addAll(helper.getPlasmaDonorsGroupwise("B-ve"));

                }
                else{
                    lst = helper.getPlasmaDonorByCity(bloodGrpPlace,"O+ve");
                    lst.addAll(helper.getPlasmaDonorByCity(bloodGrpPlace,"O-ve"));
                    lst.addAll(helper.getPlasmaDonorByCity(bloodGrpPlace,"AB+ve"));
                    lst.addAll(helper.getPlasmaDonorByCity(bloodGrpPlace,"AB-ve"));
                    lst.addAll(helper.getPlasmaDonorByCity(bloodGrpPlace,"A+ve"));
                    lst.addAll(helper.getPlasmaDonorByCity(bloodGrpPlace,"A-ve"));
                    lst.addAll(helper.getPlasmaDonorByCity(bloodGrpPlace,"B+ve"));
                    lst.addAll(helper.getPlasmaDonorByCity(bloodGrpPlace,"B-ve"));


                }
                if (lst.size() == 0) {
                    Toast.makeText(DisplayPlasmaDonorActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DisplayPlasmaDonorActivity.this, lst.size()+" donors found!", Toast.LENGTH_SHORT).show();
                }
                PlasmaDonorAdapter adapter = new PlasmaDonorAdapter(lst, DisplayPlasmaDonorActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayPlasmaDonorActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);

            }
        });





        searchCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = searchCity.getText().toString().toLowerCase();
                bloodGrpPlace = city;
                ArrayList<PlasmaDonorModel> lst;
                if(city.equals("")){
                    Toast.makeText(DisplayPlasmaDonorActivity.this, "Enter a city", Toast.LENGTH_LONG).show();
                }
                else{

                    if(bloodGrpSet.isEmpty()){
                        lst = helper.getPlasmaDonorByCity(city);
                    }
                    else{
                        switch (bloodGrpSet){
                            case "A+ve":
                            case "A-ve":
                                lst = helper.getPlasmaDonorByCity(city,"A+ve");
                                lst.addAll(helper.getPlasmaDonorByCity(city,"A-ve"));
                                lst.addAll(helper.getPlasmaDonorByCity(city,"AB+ve"));
                                lst.addAll(helper.getPlasmaDonorByCity(city,"AB-ve"));
                                break;
                            case "B+ve":
                            case "B-ve":
                                lst = helper.getPlasmaDonorByCity(city,"B+ve");
                                lst.addAll(helper.getPlasmaDonorByCity(city,"B-ve"));
                                lst.addAll(helper.getPlasmaDonorByCity(city,"AB+ve"));
                                lst.addAll(helper.getPlasmaDonorByCity(city,"AB-ve"));
                                break;
                            case "O+ve":
                            case "O-ve":
                                lst = helper.getPlasmaDonorByCity(city,"O+ve");
                                lst.addAll(helper.getPlasmaDonorByCity(city,"O-ve"));
                                lst.addAll(helper.getPlasmaDonorByCity(city,"AB+ve"));
                                lst.addAll(helper.getPlasmaDonorByCity(city,"AB-ve"));
                                lst.addAll(helper.getPlasmaDonorByCity(city,"A+ve"));
                                lst.addAll(helper.getPlasmaDonorByCity(city,"A-ve"));
                                lst.addAll(helper.getPlasmaDonorByCity(city,"B+ve"));
                                lst.addAll(helper.getPlasmaDonorByCity(city,"B-ve"));
                                break;
                            case "AB+ve":
                            case "AB-ve":
                                lst = helper.getPlasmaDonorByCity(city,"AB+ve");
                                lst.addAll(helper.getPlasmaDonorByCity(city,"AB-ve"));

                                break;

                            default:
                                lst = helper.getPlasmaDonors();
                        }
                    }

                    if(list.size() == 0){
                        Toast.makeText(DisplayPlasmaDonorActivity.this, "No donors found", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(DisplayPlasmaDonorActivity.this, lst.size()+" donors found!", Toast.LENGTH_SHORT).show();
                    }

                    PlasmaDonorAdapter adapter = new PlasmaDonorAdapter(lst,DisplayPlasmaDonorActivity.this);
                    recyclerView.setAdapter(adapter);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayPlasmaDonorActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    //Toast.makeText(DisplayPlasmaDonorActivity.this, lst.size() + " donor(s) found", Toast.LENGTH_SHORT).show();

                }

            }
        });


        undoSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGrpSet = "";
                bloodGrpPlace = "";

                ArrayList<PlasmaDonorModel> list = helper.getPlasmaDonors();

                if (list.size() == 0) {
                    Toast.makeText(DisplayPlasmaDonorActivity.this, "No donors found.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DisplayPlasmaDonorActivity.this, list.size()+" donors found!", Toast.LENGTH_SHORT).show();
                }
                searchCity.setText("");
                PlasmaDonorAdapter adapter = new PlasmaDonorAdapter(list,DisplayPlasmaDonorActivity.this);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayPlasmaDonorActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);

                //Toast.makeText(DisplayPlasmaDonorActivity.this, list.size() + " donor(s) found", Toast.LENGTH_SHORT).show();
            }
        });

    }

}