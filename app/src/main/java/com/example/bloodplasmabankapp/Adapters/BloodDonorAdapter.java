package com.example.bloodplasmabankapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_bloodgrp,tv_city;

        public viewholder(@NonNull View itemView) {

            super(itemView);
            tv_name = itemView.findViewById(R.id.name_id);
            tv_bloodgrp = itemView.findViewById(R.id.bloodgroup_id);
            tv_city = itemView.findViewById(R.id.city_id);
        }
    }
}
