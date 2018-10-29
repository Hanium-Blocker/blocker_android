package com.example.choejun_yeong.blocker_android.fragment.turnout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.activity.AdminPagerAdapter;
import com.example.choejun_yeong.blocker_android.fragment.turnout.adapter.TurnoutPagerAdapter;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

import static android.app.Activity.RESULT_OK;
import static com.darsh.multipleimageselect.helpers.Constants.REQUEST_CODE;

public class TurnoutMainFragment extends Fragment {
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @BindView(R.id.turnout_tl) TabLayout tabLayout;
    @BindView(R.id.turnout_viewpager) ViewPager viewpager;

    TextView text;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_turnout_main, container, false);
        ButterKnife.bind(this,view);

        tabLayout.addTab(tabLayout.newTab().setText("투표율"));
        tabLayout.addTab(tabLayout.newTab().setText("득표율"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewpager.setOverScrollMode(View.OVER_SCROLL_NEVER);

        TurnoutPagerAdapter pagerAdapter = new TurnoutPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewpager.setAdapter(pagerAdapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }

}