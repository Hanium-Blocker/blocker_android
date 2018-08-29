package com.example.choejun_yeong.blocker_android.network;


import com.example.choejun_yeong.blocker_android.DataModel.AuthResponse;
import com.example.choejun_yeong.blocker_android.DataModel.Candidate;
import com.example.choejun_yeong.blocker_android.DataModel.Election;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ElectionAPIService {

    @GET("election/")
    Observable<List<Election>> getElections();

    @POST("election/")
    Observable<AuthResponse> addElection(@Body Election election);

    @DELETE("election/{electionId}")
    Observable<AuthResponse> deleteElection(@Path("electionId") int electionId);
}
