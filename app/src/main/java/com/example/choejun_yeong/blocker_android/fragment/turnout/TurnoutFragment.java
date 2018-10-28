package com.example.choejun_yeong.blocker_android.fragment.turnout;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.util.ContractUtil;
import com.github.lzyzsd.circleprogress.DonutProgress;

import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.RemoteCall;

import java.math.BigInteger;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;

public class TurnoutFragment extends Fragment{
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @BindView(R.id.turnout_voter_donut_progress)
    DonutProgress donutProgress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_turnout_voter, container, false);


        return view;
    }
}
