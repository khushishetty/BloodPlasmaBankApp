package com.example.bloodplasmabankapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bloodplasmabankapp.Adapters.RequestBloodDonorAdapter;
import com.example.bloodplasmabankapp.DB.Db_Helper_Requests;
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

        Db_Helper_Requests helper = new Db_Helper_Requests(container.getContext());

        //boolean b = helper.insertRequest("Padma","2345678913","B+ve","padma@gmail.com","Mumbai","blood","Yes");
        //Toast.makeText(container.getContext(), "Status"+b, Toast.LENGTH_SHORT).show();
        ArrayList<RequestBloodDonorModel> list = helper.getBloodRequests();

        RequestBloodDonorAdapter adapter = new RequestBloodDonorAdapter(list,container.getContext());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
}