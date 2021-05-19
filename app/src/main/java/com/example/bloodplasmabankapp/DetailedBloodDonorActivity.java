package com.example.bloodplasmabankapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import android.os.Looper;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodplasmabankapp.DB.DBHelper;
import com.example.bloodplasmabankapp.DB.DB_Plasma_Helper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DetailedBloodDonorActivity extends AppCompatActivity {
    TextView name,address,bloodgrp,gender,ailments;
    String phno,mail;
    ImageView call,message,email;

    String dest;

    String globPhone, globtype;



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[grantResults.length-1] == PackageManager.PERMISSION_GRANTED){
                    phoneCall();
                }
            else{
                Toast.makeText(this, "Permission Denied !!\nPlease change permissions under App Settings!!", Toast.LENGTH_SHORT).show();
                }
        }
        else if(requestCode == 2){
            if(grantResults.length > 0 && grantResults[grantResults.length-1] == PackageManager.PERMISSION_GRANTED){
                smsFunc(globtype);
            }
            else{
                Toast.makeText(this, "Permission Denied !!\nPlease change permissions under App Settings!!", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode == 3){
            if(grantResults.length > 0 && grantResults[grantResults.length-1] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
            else{
                Toast.makeText(this, "Permission Denied !!\nPlease change permissions under App Settings!!", Toast.LENGTH_SHORT).show();
            }
        }
    }



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

            dest = address.getText().toString();
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(DetailedBloodDonorActivity.this).setIcon(R.drawable.ic_baseline_email_24)
                            .setTitle("Email")
                            .setMessage("Are you sure You want to send the email?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    /*
                                    Intent i = new Intent(Intent.ACTION_SENDTO);
                                    i.setType("text/plain");
                                    i.putExtra(Intent.EXTRA_SUBJECT, "Urgent blood request");
                                    i.putExtra(Intent.EXTRA_TEXT, "In urgent need of blood!!");
                                    i.setData(Uri.parse("mailto:" + mail));

                                    startActivity(i);

                                     */

                                    String[] TO = {mail};
                                    Intent i = new Intent(Intent.ACTION_SEND);
                                    i.setData(Uri.parse("mailto:"));
                                    i.setType("text/plain");
                                    i.putExtra(Intent.EXTRA_EMAIL, TO);
                                    i.putExtra(Intent.EXTRA_SUBJECT, "Urgent need of blood.");
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
                    globPhone = phoneno;
                    phoneCall();

                }


            });


            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   globPhone = phoneno;
                   globtype = "Blood";
                   smsFunc("Blood");
                }
            });

            address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(DetailedBloodDonorActivity.this, address.getText(), Toast.LENGTH_SHORT).show();
                    /*
                    try{
                        Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+ "Moodbidri" +"/"+destination);
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        intent.setPackage("com.google.android.apps.maps");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }catch(Exception e){
                        Toast.makeText(DetailedBloodDonorActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    }

                     */


                    if (ContextCompat.checkSelfPermission(
                            getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(DetailedBloodDonorActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                3);
                    } else {
                        getCurrentLocation();

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
            dest = address.getText().toString();
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(DetailedBloodDonorActivity.this).setIcon(R.drawable.ic_baseline_email_24)
                            .setTitle("Email")
                            .setMessage("Are you sure You want to send the email?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String[] TO = {mail};
                                    Intent i = new Intent(Intent.ACTION_SEND);
                                    i.setData(Uri.parse("mailto:"));
                                    i.setType("text/plain");
                                    i.putExtra(Intent.EXTRA_EMAIL, TO);
                                    i.putExtra(Intent.EXTRA_SUBJECT, "Urgent need of plasma.");
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
                    globPhone = phoneno;
                    phoneCall();
                }
            });

            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    globPhone = phoneno;
                    globtype = "Plasma";
                    smsFunc("Plasma");
                }
            });

            address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(DetailedBloodDonorActivity.this, address.getText(), Toast.LENGTH_SHORT).show();
                    /*
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

                     */
                    if (ContextCompat.checkSelfPermission(
                            getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(DetailedBloodDonorActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                3);
                    } else {
                        getCurrentLocation();

                    }



                }
            });
        }




    }

    private void phoneCall() {
        new AlertDialog.Builder(DetailedBloodDonorActivity.this).setIcon(R.drawable.call_icon)
                .setTitle("Call")
                .setMessage("Are you sure You want to call?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(ContextCompat.checkSelfPermission(DetailedBloodDonorActivity.this,
                                Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(DetailedBloodDonorActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
                        }else{
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:"+globPhone));
                            startActivity(intent);
                        }



                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }
    private void smsFunc(String type){
        new AlertDialog.Builder(DetailedBloodDonorActivity.this).setIcon(R.drawable.ic_baseline_message_24)
                .setTitle("Text Message")
                .setMessage("Are you sure You want to send the message?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(ContextCompat.checkSelfPermission(DetailedBloodDonorActivity.this,
                                Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(DetailedBloodDonorActivity.this, new String[]{Manifest.permission.SEND_SMS},2);
                        }
                        else{
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+globPhone));
                            i.putExtra("sms_body","Urgent need of "+ type);
                            startActivity(i);
                        }

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
    private void getCurrentLocation() {

        String result = null;
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //Toast.makeText(this, "Please turn on location", Toast.LENGTH_SHORT).show();
            return ;
        }
        LocationServices.getFusedLocationProviderClient(DetailedBloodDonorActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(@NonNull LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(DetailedBloodDonorActivity.this)
                                .removeLocationUpdates(this);
                        if(locationResult !=null && locationResult.getLocations().size()>0){
                            int latestLocationIndex  = locationResult.getLocations().size()-1;
                            double latitude =
                                    locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();



                            Geocoder geocoder = new Geocoder(DetailedBloodDonorActivity.this, Locale.getDefault());
                            List<Address> addressList = null;
                            try {
                                addressList = geocoder.getFromLocation(latitude, longitude, 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (addressList != null && addressList.size() > 0) {
                                Address address = addressList.get(0);
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                                    sb.append(address.getAddressLine(i)); //.append("\n");
                                }

                                sb.append(address.getAddressLine(0));
                                String s = sb.toString();
                                //Toast.makeText(DetailedBloodDonorActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
                                //String destination = address.getText().toString();
                                try{
                                    Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+s+"/"+dest);
                                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                                    intent.setPackage("com.google.android.apps.maps");
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }catch(Exception e){
                                    Toast.makeText(DetailedBloodDonorActivity.this, "Error", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                        else{
                            Toast.makeText(DetailedBloodDonorActivity.this, "Turn on location", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, Looper.getMainLooper());

    }
}