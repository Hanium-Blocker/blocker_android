package com.example.choejun_yeong.blocker_android.fragment.voting;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.util.ContractUtil;

import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;

import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by whit3hawks on 11/16/16.
 */
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {


    private Context context;
    private ContractUtil contractUtil;
    private int candidateId;//TODO: 체크된 후보자 id 가져와서 입력


    // Constructor
    public FingerprintHandler(Context mContext,int candidateId) {
        context = mContext;
        this.candidateId = candidateId;
        contractUtil = new ContractUtil(context);
    }


    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }


    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update("Fingerprint Authentication error\n" + errString, false);
    }


    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update("Fingerprint Authentication help\n" + helpString, false);
    }


    @Override
    public void onAuthenticationFailed() {
        this.update("지문이 일치하지 않습니다.", false);
    }


    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("지문인증 완료.\n투표완료까지 잠시 기다려 주세요.", true);

        contractUtil.voting(candidateId)
                .subscribeOn(Schedulers.computation())
                .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .onErrorReturn(new Func1<Throwable, TransactionReceipt>() {
                    @Override
                    public TransactionReceipt call(Throwable throwable) {
                        Log.d("@@@ERROR in Voting", throwable.getMessage());
                        return null;
                    }
                })
                .subscribe(x -> {
                    Toast.makeText(context, "투표 완료 ", Toast.LENGTH_SHORT).show();
                    this.update("투표가 완료 되었습니다.\n이전 화면으로 돌아가셔도 좋습니다.",true);
                });
//        contractUtil.voting() TODO: 후보자 id 가져와서 voting기능 구현.
    }


    public void update(String e, Boolean success){
        TextView textView = (TextView) ((Activity)context).findViewById(R.id.errorText);
        textView.setText(e);
        if(success){
            textView.setTextColor(ContextCompat.getColor(context,R.color.successColor));
        }
    }
}