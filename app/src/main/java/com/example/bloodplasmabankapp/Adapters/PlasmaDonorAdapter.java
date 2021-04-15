package com.example.bloodplasmabankapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedBloodDonorActivity.class);
                intent.putExtra("phone_no",model.getPhno().toString());
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder{

        TextView donorName,donorCity,donorPlasma;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            donorName = itemView.findViewById(R.id.plasma_name_id);
            donorCity = itemView.findViewById(R.id.plasma_city_id);
            donorPlasma = itemView.findViewById(R.id.plasma_blood_grp_id);
            //donorPlasma = itemView.findViewById(R.id.plasmatypeid);

        }
    }
}

