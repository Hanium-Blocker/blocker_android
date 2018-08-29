package com.example.choejun_yeong.blocker_android.service;

import com.example.choejun_yeong.blocker_android.DataModel.Election;
import com.example.choejun_yeong.blocker_android.network.APIUtiles;
import com.example.choejun_yeong.blocker_android.network.ElectionAPIService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ElectionService {
    private static final ElectionService ourInstance = new ElectionService();
    private ElectionAPIService mService;

    public static ElectionService getInstance() {
        return ourInstance;
    }

    private ElectionService() {
    }

    public void setService() {
        mService = APIUtiles.getElectionService();
    }

    public Observable<List<Election>> getElectionlist(){
        mService = APIUtiles.getElectionService();

        return mService.getElections()
                .subscribeOn(Schedulers.io())
                .map(it -> it);
    }


}
