package com.example.choejun_yeong.blocker_android;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.choejun_yeong.blocker_android.SharedMemory.PreferenceManager;

public class BlockerApplication extends Application {

    @NonNull
    private static BlockerApplication blockerApplication;

    public BlockerApplication() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        blockerApplication = this;
        setPreferences();
    }

    public void setPreferences() {
        PreferenceManager.setManager(this);
    }
}
