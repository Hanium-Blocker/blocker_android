package com.example.choejun_yeong.blocker_android.service;

import com.example.choejun_yeong.blocker_android.DataModel.AuthResponse;
import com.example.choejun_yeong.blocker_android.DataModel.ElectionVO;
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

    public Observable<List<ElectionVO>> getElectionlist(){
        mService = APIUtiles.getElectionService();

        return mService.getElections()
                .subscribeOn(Schedulers.io())
                .map(it -> it);
    }

    public Observable<AuthResponse> addElection(ElectionVO election){
        mService = APIUtiles.getElectionService();

        return mService.addElection(election)
                .subscribeOn(Schedulers.io())
                .map(it->it);
    }

    public Observable<AuthResponse> deleteElection(int electionId){
        mService = APIUtiles.getElectionService();

        return mService.deleteElection(electionId)
                .subscribeOn(Schedulers.io())
                .map(it->it);
    }

    public Observable<AuthResponse> modifyElection(int electionId,ElectionVO election){
        mService = APIUtiles.getElectionService();

        return mService.modifyElection(electionId,election)
                .subscribeOn(Schedulers.io())
                .map(it->it);
    }

}
