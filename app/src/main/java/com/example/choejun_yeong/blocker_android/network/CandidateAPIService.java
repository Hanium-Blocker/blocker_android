package com.example.choejun_yeong.blocker_android.network;


import com.example.choejun_yeong.blocker_android.DataModel.AuthResponse;
import com.example.choejun_yeong.blocker_android.DataModel.Candidate;
import com.example.choejun_yeong.blocker_android.DataModel.CandidateUpload;
import com.example.choejun_yeong.blocker_android.DataModel.ElectionVO;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface CandidateAPIService {

    @GET("election/")
    Observable<List<ElectionVO>> getElections();

    @GET("election/{electionId}/candidate/")
    Observable<List<Candidate>> getCandidates(@Path("electionId") int election_id);

//    @Multipart
    @POST("election/{electionId}/candidate/")
    Observable<AuthResponse> addCandidate(@Path("electionId") int electionId, @Body CandidateUpload candidate);

    @Multipart
    @POST("election/{electionId}/candidate/{number}/{name}")
    Observable<AuthResponse> addCandidateImage(@Path("electionId") int electionId, @Path("number") int number, @Path("name") String name,@Part MultipartBody.Part image);

    @DELETE("election/{electionId}/candidate/{number}/")
    Observable<AuthResponse> deleteCandidate(@Path("electionId") int electionId, @Path("number") int number);

    @PUT("election/{electionId}/candidate/{number}/")
    Observable<AuthResponse> modifyCandidate(@Path("electionId") int electionId, @Path("number") int number, @Body CandidateUpload candidate);

}
