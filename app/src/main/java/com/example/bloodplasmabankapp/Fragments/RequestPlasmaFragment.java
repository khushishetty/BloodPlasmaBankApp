package com.example.bloodplasmabankapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodplasmabankapp.Adapters.RequestBloodDonorAdapter;
import com.example.bloodplasmabankapp.Adapters.RequestPlasmaDonorAdapter;
import com.example.bloodplasmabankapp.DB.Db_Helper_Requests;
import com.example.bloodplasmabankapp.Models.RequestBloodDonorModel;
import com.example.bloodplasmabankapp.Models.RequestPlasmaDonorModel;
import com.example.bloodplasmabankapp.R;

import java.util.ArrayList;

public class RequestPlasmaFragment extends Fragment {
    RecyclerView recyclerView;
    public RequestPlasmaFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request_plasma, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.request_plasma_rv);

        Db_Helper_Requests helper = new Db_Helper_Requests(container.getContext());

        ArrayList<RequestPlasmaDonorModel> list = helper.getPlasmaRequests();

        RequestPlasmaDonorAdapter adapter = new RequestPlasmaDonorAdapter(list,container.getContext());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
}