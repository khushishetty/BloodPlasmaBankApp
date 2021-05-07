package com.example.bloodplasmabankapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodplasmabankapp.Adapters.RequestBloodDonorAdapter;
import com.example.bloodplasmabankapp.Adapters.RequestPlasmaDonorAdapter;
import com.example.bloodplasmabankapp.Models.RequestBloodDonorModel;
import com.example.bloodplasmabankapp.Models.RequestPlasmaDonorModel;

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

        ArrayList<RequestPlasmaDonorModel> list = new ArrayList<>();
        list.add(new RequestPlasmaDonorModel("Khushi","B-ve","Bangalore","20/5/20","9886724699","khushi@gmail.com","12:00"));
        list.add(new RequestPlasmaDonorModel("Jaya","O+ve","Bangalore","20/5/20","9886724699","khushi@gmail.com","12:30"));
        list.add(new RequestPlasmaDonorModel("Khushi","A+ve","Mangalore","20/5/20","9886724699","khushi@gmail.com","11:23"));
        list.add(new RequestPlasmaDonorModel("Khushi","B-ve","Bangalore","20/5/20","9886724699","khushi@gmail.com","12:00"));
        list.add(new RequestPlasmaDonorModel("Jaya","O+ve","Bangalore","20/5/20","9886724699","khushi@gmail.com","12:30"));
        list.add(new RequestPlasmaDonorModel("Khushi","A+ve","Mangalore","20/5/20","9886724699","khushi@gmail.com","11:23"));
        list.add(new RequestPlasmaDonorModel("Khushi","B-ve","Bangalore","20/5/20","9886724699","khushi@gmail.com","12:00"));
        list.add(new RequestPlasmaDonorModel("Jaya","O+ve","Bangalore","20/5/20","9886724699","khushi@gmail.com","12:30"));
        list.add(new RequestPlasmaDonorModel("Khushi","A+ve","Mangalore","20/5/20","9886724699","khushi@gmail.com","11:23"));
        list.add(new RequestPlasmaDonorModel("Khushi","B-ve","Bangalore","20/5/20","9886724699","khushi@gmail.com","12:00"));
        list.add(new RequestPlasmaDonorModel("Jaya","O+ve","Bangalore","20/5/20","9886724699","khushi@gmail.com","12:30"));
        list.add(new RequestPlasmaDonorModel("Khushi","A+ve","Mangalore","20/5/20","9886724699","khushi@gmail.com","11:23"));
        list.add(new RequestPlasmaDonorModel("Khushi","B-ve","Bangalore","20/5/20","9886724699","khushi@gmail.com","12:00"));
        list.add(new RequestPlasmaDonorModel("Jaya","O+ve","Bangalore","20/5/20","9886724699","khushi@gmail.com","12:30"));
        list.add(new RequestPlasmaDonorModel("Khushi","A+ve","Mangalore","20/5/20","9886724699","khushi@gmail.com","11:23"));

        RequestPlasmaDonorAdapter adapter = new RequestPlasmaDonorAdapter(list,container.getContext());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
}