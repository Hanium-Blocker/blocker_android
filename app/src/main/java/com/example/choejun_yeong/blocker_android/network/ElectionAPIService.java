package com.example.choejun_yeong.blocker_android.network;


import com.example.choejun_yeong.blocker_android.DataModel.Candidate;
import com.example.choejun_yeong.blocker_android.DataModel.Election;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ElectionAPIService {

    @GET("election/")
    Observable<List<Election>> getElections();

}
