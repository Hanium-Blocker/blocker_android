package com.example.choejun_yeong.blocker_android.network;


import com.example.choejun_yeong.blocker_android.BuildConfig;

public class APIUtiles {
    private APIUtiles() { }

//    private static final String BASE_URL = BuildConfig.APIServerBaseURL;
    private static final String BASE_URL = "http://192.168.0.6:3000/";
    public static final String CANDIDATE_API_URL = BASE_URL + "user/";
    public static final String AUTH_API_URL = BASE_URL + "auth/";


    public static CandidateAPIService getCandidateService(){
        return RetrofitClient.getClient(CANDIDATE_API_URL).create(CandidateAPIService.class);
    }

    public static AuthAPIService getAuthService(){
        return RetrofitClient.getClient(AUTH_API_URL).create(AuthAPIService.class);
    }
}