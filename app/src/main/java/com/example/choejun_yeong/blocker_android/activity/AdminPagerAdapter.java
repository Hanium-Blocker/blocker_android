package com.example.choejun_yeong.blocker_android.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.choejun_yeong.blocker_android.fragment.admin_fragment.ElectionManageFragment;
import com.example.choejun_yeong.blocker_android.fragment.candidate_info.CandidateMainFragment;
import com.example.choejun_yeong.blocker_android.fragment.turnout.TurnoutMainFragment;
import com.example.choejun_yeong.blocker_android.fragment.voting.VotingMainFragment;

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
                return new ElectionManageFragment();
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

