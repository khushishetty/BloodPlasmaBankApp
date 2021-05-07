package com.example.bloodplasmabankapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodplasmabankapp.Models.RequestBloodDonorModel;
import com.example.bloodplasmabankapp.R;

import java.util.ArrayList;

public class RequestBloodDonorAdapter extends RecyclerView.Adapter<RequestBloodDonorAdapter.viewHolder> {

    ArrayList<RequestBloodDonorModel> list;
    Context context;

    public RequestBloodDonorAdapter() {
    }

    public RequestBloodDonorAdapter(ArrayList<RequestBloodDonorModel> list, Context context) {
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
        final RequestBloodDonorModel model = list.get(position);
        holder.r_name.setText(model.getName());
        holder.r_bloodgrp.setText(model.getBloodgrp());
        holder.r_address.setText(model.getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder{

        TextView r_bloodgrp, r_name, r_address, r_time;
        ImageButton call,message,email;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            r_bloodgrp = itemView.findViewById(R.id.request_blood_grp_id);
            r_name = itemView.findViewById(R.id.request_name_id);
            r_address = itemView.findViewById(R.id.request_city_id);
            r_time = itemView.findViewById(R.id.request_date);

            call = itemView.findViewById(R.id.request_call_btn_id);
            message = itemView.findViewById(R.id.request_mail_btn_id);
            email = itemView.findViewById(R.id.request_email_btn_id);


        }
    }
}
