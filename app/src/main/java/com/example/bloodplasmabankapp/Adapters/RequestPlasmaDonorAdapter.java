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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodplasmabankapp.Models.RequestBloodDonorModel;
import com.example.bloodplasmabankapp.Models.RequestPlasmaDonorModel;
import com.example.bloodplasmabankapp.R;

import java.util.ArrayList;

public class RequestPlasmaDonorAdapter extends RecyclerView.Adapter<RequestPlasmaDonorAdapter.viewHolder> {

    ArrayList<RequestPlasmaDonorModel> list;
    Context context;

    public RequestPlasmaDonorAdapter() {
    }

    public RequestPlasmaDonorAdapter(ArrayList<RequestPlasmaDonorModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recycler_view_3,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final RequestPlasmaDonorModel model = list.get(position);
        holder.r_name.setText(model.getName());
        holder.r_bloodgrp.setText(model.getBloodgrp());
        holder.r_address.setText(model.getAddress());
        holder.r_time.setText(model.getTime());
        holder.r_type.setText(model.getType());
        if(model.getUrgent().equals("Yes")){
            holder.r_urgent.setVisibility(View.VISIBLE);
        }
        String ph = model.getPhone().toString();
        
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context).setIcon(R.drawable.ic_baseline_message_24)
                        .setTitle("Text Message")
                        .setMessage("Are you sure You want to send the message?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+ph));
                                i.putExtra("sms_body","Plasma found");
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

        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context).setIcon(R.drawable.ic_baseline_email_24)
                        .setTitle("Email")
                        .setMessage("Are you sure You want to send the email?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String[] TO = {model.getEmail().toString()};
                                Intent i = new Intent(Intent.ACTION_SEND);
                                i.setData(Uri.parse("mailto:"));
                                i.setType("text/plain");
                                i.putExtra(Intent.EXTRA_EMAIL, TO);
                                i.putExtra(Intent.EXTRA_SUBJECT, "In response to the plasma request made.");
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public  class viewHolder extends RecyclerView.ViewHolder{

        TextView r_bloodgrp, r_name, r_address, r_time, r_type;
        ImageButton call,message,email;
        LinearLayout r_urgent;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            r_bloodgrp = itemView.findViewById(R.id.request_blood_grp_id);
            r_name = itemView.findViewById(R.id.request_name_id);
            r_address = itemView.findViewById(R.id.request_city_id);
            r_time = itemView.findViewById(R.id.request_date);
            r_type = itemView.findViewById(R.id.requestTypeid);

            call = itemView.findViewById(R.id.request_call_btn_id);
            message = itemView.findViewById(R.id.request_mail_btn_id);
            email = itemView.findViewById(R.id.request_email_btn_id);
            r_urgent = itemView.findViewById(R.id.request_urgent_layout);

        }
    }
}
