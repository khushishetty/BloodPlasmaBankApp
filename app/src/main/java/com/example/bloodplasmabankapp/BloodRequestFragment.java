package com.example.bloodplasmabankapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodplasmabankapp.Adapters.RequestBloodDonorAdapter;
import com.example.bloodplasmabankapp.Models.RequestBloodDonorModel;

import java.util.ArrayList;

public class BloodRequestFragment extends Fragment {

    RecyclerView recyclerView;

    public BloodRequestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blood_request, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.request_page_recycler_view);

        ArrayList<RequestBloodDonorModel>list = new ArrayList<>();
        list.add(new RequestBloodDonorModel("Khushi","B-ve","Bangalore","20/5/20","9886724699","khushi@gmail.com","12:00"));
        list.add(new RequestBloodDonorModel("Khushi","O+ve","Bangalore","20/5/20","9886724699","khushi@gmail.com","12:00"));
        list.add(new RequestBloodDonorModel("Khushi","A+ve","Mangalore","20/5/20","9886724699","khushi@gmail.com","12:00"));

        RequestBloodDonorAdapter adapter = new RequestBloodDonorAdapter(list,container.getContext());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
}