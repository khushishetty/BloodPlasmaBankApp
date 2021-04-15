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

import com.example.bloodplasmabankapp.DetailedBloodDonorActivity;
import com.example.bloodplasmabankapp.Models.PlasmaDonorModel;
import com.example.bloodplasmabankapp.R;

import java.util.ArrayList;

public class PlasmaDonorAdapter  extends  RecyclerView.Adapter<PlasmaDonorAdapter.viewHolder>{

    ArrayList<PlasmaDonorModel> list;
    Context context;

    public PlasmaDonorAdapter(ArrayList<PlasmaDonorModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recycler_view_2,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final PlasmaDonorModel model = list.get(position);
        holder.donorName.setText(model.getName());
        holder.donorCity.setText(model.getCity());
        holder.donorPlasma.setText(model.getPlasmagrp());
        String ph = model.getPhno().toString();
        String mail_id = model.getEmail().toString();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedBloodDonorActivity.class);
                intent.putExtra("phone_no",model.getPhno().toString());
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });

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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder{

        TextView donorName,donorCity,donorPlasma;
        ImageButton call,message,email;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            donorName = itemView.findViewById(R.id.plasma_name_id);
            donorCity = itemView.findViewById(R.id.plasma_city_id);
            donorPlasma = itemView.findViewById(R.id.plasma_blood_grp_id);
            //donorPlasma = itemView.findViewById(R.id.plasmatypeid);
            call = itemView.findViewById(R.id.profile_call_btn_id);
            message = itemView.findViewById(R.id.profile_mail_btn_id);
            email= itemView.findViewById(R.id.profile_email_btn_id);

        }
    }
}

