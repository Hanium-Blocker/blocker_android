package com.example.choejun_yeong.blocker_android.network;


import com.example.choejun_yeong.blocker_android.DataModel.AuthResponse;
import com.example.choejun_yeong.blocker_android.DataModel.Candidate;
import com.example.choejun_yeong.blocker_android.DataModel.CandidateUpload;
import com.example.choejun_yeong.blocker_android.DataModel.Election;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CandidateAPIService {

    @GET("election/")
    Observable<List<Election>> getElections();

    @GET("election/{electionId}/candidate/")
    Observable<List<Candidate>> getCandidates(@Path("electionId") int election_id);

    @POST("election/{electionId}/candidate/")
    Observable<AuthResponse> addCandidate(@Path("electionId") int electionId, @Body CandidateUpload candidate);

    @DELETE("election/{electionId}/candidate/{number}/")
    Observable<AuthResponse> deleteCandidate(@Path("electionId") int electionId, @Path("number") int number);

}
