package com.example.choejun_yeong.blocker_android.network;

import com.example.choejun_yeong.blocker_android.DataModel.AuthResponse;
import com.example.choejun_yeong.blocker_android.DataModel.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthAPIService {
    @POST("login/")
    Observable<AuthResponse> Login(@Body UserInfo userInfo);

    @POST("register/")
    Observable<AuthResponse> Signup(@Body UserInfo userInfo);

}
