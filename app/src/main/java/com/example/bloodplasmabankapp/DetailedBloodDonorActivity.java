package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

import org.w3c.dom.Text;

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

        }
    }
}