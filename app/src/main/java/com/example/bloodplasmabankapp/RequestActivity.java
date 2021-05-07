package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.bloodplasmabankapp.Adapters.FragmentReqAdapter;
import com.google.android.material.tabs.TabLayout;

public class RequestActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        viewPager = findViewById(R.id.viewPagerid);
        viewPager.setAdapter(new FragmentReqAdapter(getSupportFragmentManager()));

        tabLayout = findViewById(R.id.tablayoutid);
        tabLayout.setupWithViewPager(viewPager);
    }
}