package com.example.devfest.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.devfest.Fragments.CloudFragment;
import com.example.devfest.Fragments.MobileFragment;
import com.example.devfest.Fragments.WebFragment;

public class AgendaMenuAdapter extends FragmentStateAdapter {

    public AgendaMenuAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new CloudFragment();
            case 1:
                return new MobileFragment();
            default:
                return new WebFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
