package com.example.choejun_yeong.blocker_android.fragment.turnout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.choejun_yeong.blocker_android.R;

import io.reactivex.disposables.CompositeDisposable;

public class ScoreFragment extends Fragment {
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_turnout_score, container, false);



        return view;
    }
}
