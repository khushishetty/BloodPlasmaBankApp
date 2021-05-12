package com.example.bloodplasmabankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.bloodplasmabankapp.Adapters.FragmentCheckAdapter;
import com.example.bloodplasmabankapp.Adapters.FragmentReqAdapter;
import com.google.android.material.tabs.TabLayout;

public class CheckEligibilityActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_eligibility);

        viewPager = findViewById(R.id.viewPagerid2);
        viewPager.setAdapter(new FragmentCheckAdapter(getSupportFragmentManager()));

        tabLayout = findViewById(R.id.tablayoutid2);
        tabLayout.setupWithViewPager(viewPager);
    }
}