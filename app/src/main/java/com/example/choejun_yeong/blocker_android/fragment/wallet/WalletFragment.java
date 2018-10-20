package com.example.choejun_yeong.blocker_android.fragment.wallet;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.web3.CreateWallet;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalletFragment extends Fragment {

    @BindView(R.id.wallet_make_btn)
    Button makeButton;
    Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_make_wallet,container,false);
        ButterKnife.bind(this,view);
        handler = new Handler();
        return view;

    }

    @OnClick(R.id.wallet_make_btn)
    public void createWallet(){
        Log.d("@@@@@","make button clicked@@@@");

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        createWallet("123");
                    }
                });
            }
        }).start();

    }


    public String[] createWallet(final String password) { //password 를 인자로 던져주면 String 배열에 wallet 이 저장된 path 와 wallet 주소를 반환 해주는 메소드
        String[] result = new String[2];
        try {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS); //다운로드 path 가져오기
            if (!path.exists()) {
                path.mkdir();
            }
            String fileName = WalletUtils.generateLightNewWalletFile(password, new File(String.valueOf(path))); //지갑생성
            result[0] = path + "/" + fileName;

            Credentials credentials = WalletUtils.loadCredentials(password, result[0]);

            result[1] = credentials.getAddress();
            Toast.makeText(getContext(), "성공", Toast.LENGTH_SHORT).show();
            return result;

        } catch (NoSuchAlgorithmException
                | NoSuchProviderException
                | InvalidAlgorithmParameterException
                | IOException
                | CipherException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
