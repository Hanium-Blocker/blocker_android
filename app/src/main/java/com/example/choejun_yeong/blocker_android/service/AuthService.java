package com.example.choejun_yeong.blocker_android.service;

import com.example.choejun_yeong.blocker_android.DataModel.AuthResponse;
import com.example.choejun_yeong.blocker_android.DataModel.UserInfo;
import com.example.choejun_yeong.blocker_android.network.APIUtiles;
import com.example.choejun_yeong.blocker_android.network.AuthAPIService;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AuthService {
    private static final AuthService ourInstance = new AuthService();
    private AuthAPIService mService;

    public static AuthService getInstance() {
        return ourInstance;
    }

    private AuthService() {
    }

    public void setService() {
        mService = APIUtiles.getAuthService();
    }

    public Observable<AuthResponse> login(UserInfo userInfo){
        mService = APIUtiles.getAuthService();

        return mService.Login(userInfo)
                .subscribeOn(Schedulers.io())
                .map(it -> it);
    }

    public Observable<AuthResponse> signUp(UserInfo userInfo){
        mService = APIUtiles.getAuthService();

        return mService.Signup(userInfo)
                .subscribeOn(Schedulers.io())
                .map(it -> it);
    }
}
