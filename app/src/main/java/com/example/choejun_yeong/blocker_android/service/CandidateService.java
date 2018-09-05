package com.example.choejun_yeong.blocker_android.service;

import android.util.Log;

import com.example.choejun_yeong.blocker_android.DataModel.AuthResponse;
import com.example.choejun_yeong.blocker_android.DataModel.Candidate;
import com.example.choejun_yeong.blocker_android.DataModel.CandidateUpload;
import com.example.choejun_yeong.blocker_android.DataModel.Candidate_Voting;
import com.example.choejun_yeong.blocker_android.DataModel.Election;
import com.example.choejun_yeong.blocker_android.network.APIUtiles;
import com.example.choejun_yeong.blocker_android.network.CandidateAPIService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class CandidateService {
    private static final CandidateService ourInstance = new CandidateService();
    private CandidateAPIService mService;

    public static CandidateService getInstance() {
        return ourInstance;
    }

    private CandidateService() {
    }

    public void setService() {
        mService = APIUtiles.getCandidateService();
    }

    public Observable<List<Election>> getElectionlist(){
        mService = APIUtiles.getCandidateService();

        return mService.getElections()
                .subscribeOn(Schedulers.io())
                .map(it -> it);
    }

    public Observable<List<Candidate>> getCandidatelist(int election_id){
        mService = APIUtiles.getCandidateService();

        return mService.getCandidates(election_id)
                .subscribeOn(Schedulers.io())
                .map(it -> it);
    }

    public Observable<AuthResponse> addCandidate(int election_id ,CandidateUpload candidate){
        mService = APIUtiles.getCandidateService();

        return mService.addCandidate(election_id, candidate)
                .subscribeOn(Schedulers.io())
                .map(it->it);
    }

    public Observable<AuthResponse> deleteCandidate(int electionId, int number){
        mService = APIUtiles.getCandidateService();

        return mService.deleteCandidate(electionId, number)
                .subscribeOn(Schedulers.io())
                .map(it->it);
    }

    public Observable<AuthResponse> modifyCandidate(int electionId, int number, Candidate candidate){
        mService = APIUtiles.getCandidateService();

        return mService.modifyCandidate(electionId, number, candidate)
                .subscribeOn(Schedulers.io())
                .map(it->it);
    }

}
