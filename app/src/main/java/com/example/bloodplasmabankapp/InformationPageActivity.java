package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InformationPageActivity extends AppCompatActivity {

    FloatingActionButton call, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_page);

        email = (FloatingActionButton)findViewById(R.id.infoEmailid);
        call = (FloatingActionButton)findViewById(R.id.infoCallId);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(InformationPageActivity.this, "Hello", Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(InformationPageActivity.this).setIcon(R.drawable.ic_baseline_email_24)
                        .setTitle("Email")
                        .setMessage("Are you sure You want to send the email?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String[] TO = {"maithili@gmail.com"};
                                Intent i = new Intent(Intent.ACTION_SEND);
                                i.setData(Uri.parse("mailto:"));
                                i.setType("text/plain");
                                i.putExtra(Intent.EXTRA_EMAIL, TO);
                                i.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
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

                new AlertDialog.Builder(InformationPageActivity.this).setIcon(R.drawable.call_icon)
                        .setTitle("Call")
                        .setMessage("Are you sure You want to call?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:" + "8105199132"));
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
    }
}