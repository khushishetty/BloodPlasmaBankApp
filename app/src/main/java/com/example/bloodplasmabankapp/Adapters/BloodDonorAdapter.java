package com.example.bloodplasmabankapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodplasmabankapp.Models.BloodDonorModel;
import com.example.bloodplasmabankapp.R;

import java.util.ArrayList;

public class BloodDonorAdapter extends RecyclerView.Adapter<BloodDonorAdapter.viewholder> {

    ArrayList<BloodDonorModel> arrayList;
    Context context;

    public BloodDonorAdapter(ArrayList<BloodDonorModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recycler_view_1,parent,false);
        return new viewholder(view);
    }
    
    @Override

    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        BloodDonorModel model = arrayList.get(position);
        holder.tv_name.setText(model.getName());
        holder.tv_bloodgrp.setText(model.getBlood_group());
        holder.tv_city.setText(model.getCity());
        String ph = model.getPhno().toString();
        String mail_id = model.getMail().toString();


        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context).setIcon(R.drawable.ic_baseline_email_24)
                        .setTitle("Email")
                        .setMessage("Are you sure You want to send the email?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(Intent.ACTION_SENDTO);
                                i.setType("text/plain");
                                i.putExtra(Intent.EXTRA_SUBJECT, "Urgent blood request");
                                i.putExtra(Intent.EXTRA_TEXT, "In urgent need of blood!!");
                                i.setData(Uri.parse("mailto:" + mail_id));

                                context.startActivity(i);
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
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context).setIcon(R.drawable.call_icon)
                .setTitle("Call")
                .setMessage("Are you sure You want to call?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:"+ph));
                        context.startActivity(intent);
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
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new AlertDialog.Builder(context).setIcon(R.drawable.ic_baseline_message_24)
                        .setTitle("Text Message")
                        .setMessage("Are you sure You want to send the message?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(ph, null, "Urgent Blood Request", null, null);
                                Toast.makeText(context, "SMS Sent", Toast.LENGTH_SHORT).show();
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_bloodgrp,tv_city;
        ImageButton call,message,email;

        public viewholder(@NonNull View itemView) {

            super(itemView);
            tv_name = itemView.findViewById(R.id.plasma_name_id);
            tv_bloodgrp = itemView.findViewById(R.id.bloodgroup_id);
            tv_city = itemView.findViewById(R.id.plasma_city_id);
            call = itemView.findViewById(R.id.plasma_call_btn_id);
            message = itemView.findViewById(R.id.plasma_mail_btn_id);
            email= itemView.findViewById(R.id.plasma_email_btn_id);
        }
    }
}
