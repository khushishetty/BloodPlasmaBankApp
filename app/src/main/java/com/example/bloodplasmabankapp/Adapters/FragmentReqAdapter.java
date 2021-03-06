package com.example.bloodplasmabankapp.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bloodplasmabankapp.Fragments.BloodRequestFragment;
import com.example.bloodplasmabankapp.Fragments.RequestPlasmaFragment;

public class FragmentReqAdapter extends FragmentPagerAdapter {

    public FragmentReqAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new BloodRequestFragment();
            case 1:
                return new RequestPlasmaFragment();
            default:
                return new RequestPlasmaFragment();
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
            title = "Blood Requests";
        }
        else{
            title = "Plasma Requests";
        }
        return title;
    }
}
