package com.example.choejun_yeong.blocker_android.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.choejun_yeong.blocker_android.R;

public class MainActivity extends AppCompatActivity {

    @NonNull
    private static TabLayout tabLayout;

    @NonNull
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.main_viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("후보자 정보"));
        tabLayout.addTab(tabLayout.newTab().setText("투표율 보기"));
        tabLayout.addTab(tabLayout.newTab().setText("투표하기"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewpager.setOverScrollMode(View.OVER_SCROLL_NEVER);

        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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
