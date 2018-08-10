package com.example.choejun_yeong.blocker_android.Schedulers;


import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

public interface ISchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler ui();
}
