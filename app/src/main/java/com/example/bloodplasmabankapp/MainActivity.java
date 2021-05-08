package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView c1,c2,c3,c4,c5,c6;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        c1 = (CardView)findViewById(R.id.bec_blood_donor_id);
        c2 = (CardView)findViewById(R.id.search_blood_donor_id);
        c3  =(CardView)findViewById(R.id.search_plasma_donor_id);
        c4 = (CardView)findViewById(R.id.serach_nearby_hospitals);
        c5 = (CardView)findViewById(R.id.view_requests_id);
        c6 = (CardView)findViewById(R.id.requestid);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Become A Donor", Toast.LENGTH_SHORT).show();

            }
        });


        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,DisplayBloodDonorsActivity.class);
                startActivity(intent);

            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DisplayPlasmaDonorActivity.class);
                startActivity(intent);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(DetailedBloodDonorActivity.this, address.getText(), Toast.LENGTH_SHORT).show();
                String source = "Alangar,Moodbidri";
                try{
                    Uri uri = Uri.parse("https://www.google.co.in/maps/search/Blood+Bank/"+source);
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    intent.setPackage("com.google.android.apps.maps");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }catch(Exception e){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RequestActivity.class);
                startActivity(intent);
            }
        });

        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RequestLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}