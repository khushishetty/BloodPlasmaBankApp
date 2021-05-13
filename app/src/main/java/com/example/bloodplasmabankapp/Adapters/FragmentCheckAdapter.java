package com.example.bloodplasmabankapp.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bloodplasmabankapp.Fragments.CheckBloodEligibilityFragment;
import com.example.bloodplasmabankapp.Fragments.CheckPlasmaEligibilityFragment;

public class FragmentCheckAdapter extends FragmentPagerAdapter {


    public FragmentCheckAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CheckBloodEligibilityFragment();
            case 1:
                return new CheckPlasmaEligibilityFragment();
            default:return new CheckBloodEligibilityFragment();

        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if(position == 0){
            title = "Blood Eligibility";
        }
        else if(position == 1){
            title = "Plasma Eligibility";
        }

        return title;
    }
}
