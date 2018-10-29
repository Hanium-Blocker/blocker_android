package com.example.choejun_yeong.blocker_android.fragment.turnout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.choejun_yeong.blocker_android.fragment.turnout.ScoreFragment;
import com.example.choejun_yeong.blocker_android.fragment.turnout.TurnoutFragment;

public class TurnoutPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public TurnoutPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new TurnoutFragment();
            case 1:
                return new ScoreFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
