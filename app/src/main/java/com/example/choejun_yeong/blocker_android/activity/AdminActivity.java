package com.example.choejun_yeong.blocker_android.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.choejun_yeong.blocker_android.R;

public class AdminActivity extends AppCompatActivity {

    @NonNull
    private static TabLayout tabLayout;

    @NonNull
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tabLayout = findViewById(R.id.admin_tablayout);
        viewpager = findViewById(R.id.admin_viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("선거 관리"));
        tabLayout.addTab(tabLayout.newTab().setText("후보자 관리"));
        tabLayout.addTab(tabLayout.newTab().setText("투표 관리"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewpager.setOverScrollMode(View.OVER_SCROLL_NEVER);

        AdminPagerAdapter pagerAdapter = new AdminPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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

    }


    public static void tabVisible(int visible){
        tabLayout.setVisibility(visible);
    }
}
