package com.example.choejun_yeong.blocker_android.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.choejun_yeong.blocker_android.fragment.candidate_info.CandidateMainFragment;
import com.example.choejun_yeong.blocker_android.fragment.turnout.TurnoutMainFragment;
import com.example.choejun_yeong.blocker_android.fragment.voting.VotingMainFragment;
import com.example.choejun_yeong.blocker_android.fragment.voting.VotingRootFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public MainPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new CandidateMainFragment();
            case 1:
                return new TurnoutMainFragment();
            case 2:
                return new VotingRootFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
