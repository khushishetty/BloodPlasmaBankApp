package com.example.bloodplasmabankapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodplasmabankapp.DB.DBHelper;
import com.example.bloodplasmabankapp.DB.DB_Plasma_Helper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

public class DetailedBloodDonorActivity extends AppCompatActivity {
    TextView name,address,bloodgrp,gender,ailments;
    String phno,mail;
    ImageView call,message,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_blood_donor);

        name = (TextView)findViewById(R.id.profile_name_id);
        address = (TextView)findViewById(R.id.profile_address_id);

        bloodgrp = (TextView)findViewById(R.id.profile_bloodgrp);
        gender = (TextView)findViewById(R.id.profile_gender);
        ailments = (TextView)findViewById(R.id.blood_ailments);
        call = (ImageView) findViewById(R.id.profile_call_btn_id);
        message = (ImageView)findViewById(R.id.profile_mail_btn_id);
        email = (ImageView)findViewById(R.id.profile_email_btn_id);

        final DBHelper dbHelper= new DBHelper(this);
        final DB_Plasma_Helper plasma_helper = new DB_Plasma_Helper(this);

        if(getIntent().getIntExtra("type",0)==1){
            final String phoneno = getIntent().getStringExtra("phone_no");
            final Cursor cursor  = dbHelper.getBloodDonorByPhone(phoneno);

            name.setText(cursor.getString(2));
            address.setText(cursor.getString(5));
            bloodgrp.setText(cursor.getString(3));
            gender.setText(cursor.getString(7));
            ailments.setText(cursor.getString(6));
            mail = cursor.getString(4);

            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(DetailedBloodDonorActivity.this).setIcon(R.drawable.ic_baseline_email_24)
                            .setTitle("Email")
                            .setMessage("Are you sure You want to send the email?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(Intent.ACTION_SENDTO);
                                    i.setType("text/plain");
                                    i.putExtra(Intent.EXTRA_SUBJECT, "Urgent blood request");
                                    i.putExtra(Intent.EXTRA_TEXT, "In urgent need of blood!!");
                                    i.setData(Uri.parse("mailto:" + mail));

                                    startActivity(i);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();

                }
            });

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(DetailedBloodDonorActivity.this).setIcon(R.drawable.call_icon)
                            .setTitle("Call")
                            .setMessage("Are you sure You want to call?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_CALL);
                                    intent.setData(Uri.parse("tel:"+phoneno));
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            });

            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(DetailedBloodDonorActivity.this).setIcon(R.drawable.ic_baseline_message_24)
                            .setTitle("Text Message")
                            .setMessage("Are you sure You want to send the message?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SmsManager smsManager = SmsManager.getDefault();
                                    smsManager.sendTextMessage(phoneno, null, "Urgent Blood Request", null, null);
                                    Toast.makeText(DetailedBloodDonorActivity.this, "SMS Sent", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            });

            address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(DetailedBloodDonorActivity.this, address.getText(), Toast.LENGTH_SHORT).show();
                    final String[] source = new String[1];

                    FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(DetailedBloodDonorActivity.this);
                    if(ActivityCompat.checkSelfPermission(DetailedBloodDonorActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                                @Override
                                public void onComplete(@NonNull Task<Location> task) {
                                    Location location = task.getResult();
                                    if(location!=null){
                                        Toast.makeText(DetailedBloodDonorActivity.this, "Current Loc "+location, Toast.LENGTH_SHORT).show();
                                        try{
                                            Geocoder geocoder = new Geocoder(DetailedBloodDonorActivity.this, Locale.getDefault());
                                            List<Address> addresses = geocoder.getFromLocation(
                                                    location.getLatitude(),location.getLongitude(),1
                                            );
                                            source[0] = addresses.get(0).getAddressLine(0);
                                        }
                                        catch (Exception e){

                                        }
                                    }
                                }
                            });
                    }
                    else{
                        ActivityCompat.requestPermissions(DetailedBloodDonorActivity.this
                        ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                    }



                    String destination = address.getText().toString();
                    try{
                        Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+ source[0] +"/"+destination);
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        intent.setPackage("com.google.android.apps.maps");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }catch(Exception e){
                        Toast.makeText(DetailedBloodDonorActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    }


                }
            });

        }

        else if(getIntent().getIntExtra("type",0)==2){
            final String phoneno = getIntent().getStringExtra("phone_no");
            final Cursor cursor  = plasma_helper.getPlasmaDonorByPhone(phoneno);

            name.setText(cursor.getString(2));
            address.setText(cursor.getString(5));
            bloodgrp.setText(cursor.getString(3));
            gender.setText(cursor.getString(7));
            ailments.setText(cursor.getString(6));
            mail = cursor.getString(4);

            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(DetailedBloodDonorActivity.this).setIcon(R.drawable.ic_baseline_email_24)
                            .setTitle("Email")
                            .setMessage("Are you sure You want to send the email?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(Intent.ACTION_SENDTO);
                                    i.setType("text/plain");
                                    i.putExtra(Intent.EXTRA_SUBJECT, "Urgent blood request");
                                    i.putExtra(Intent.EXTRA_TEXT, "In urgent need of blood!!");
                                    i.setData(Uri.parse("mailto:" + mail));

                                    startActivity(i);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();

                }
            });

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(DetailedBloodDonorActivity.this).setIcon(R.drawable.call_icon)
                            .setTitle("Call")
                            .setMessage("Are you sure You want to call?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_CALL);
                                    intent.setData(Uri.parse("tel:"+phoneno));
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            });

            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(DetailedBloodDonorActivity.this).setIcon(R.drawable.ic_baseline_message_24)
                            .setTitle("Text Message")
                            .setMessage("Are you sure You want to send the message?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SmsManager smsManager = SmsManager.getDefault();
                                    smsManager.sendTextMessage(phoneno, null, "Urgent Blood Request", null, null);
                                    Toast.makeText(DetailedBloodDonorActivity.this, "SMS Sent", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            });

            address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(DetailedBloodDonorActivity.this, address.getText(), Toast.LENGTH_SHORT).show();
                    String source = "Alangar,Moodbidri";
                    String destination = address.getText().toString();
                    try{
                        Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+destination);
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        intent.setPackage("com.google.android.apps.maps");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }catch(Exception e){
                        Toast.makeText(DetailedBloodDonorActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    }


                }
            });
        }




    }
}