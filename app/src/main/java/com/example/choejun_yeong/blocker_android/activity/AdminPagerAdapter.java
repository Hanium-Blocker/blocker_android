package com.example.choejun_yeong.blocker_android.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.choejun_yeong.blocker_android.fragment.admin_candidate.CandidateManageFragment;
import com.example.choejun_yeong.blocker_android.fragment.admin_election.ElectionManageFragment;

public class AdminPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public AdminPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new ElectionManageFragment();
            case 1:
                return new CandidateManageFragment();
            case 2:
                return new ElectionManageFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

