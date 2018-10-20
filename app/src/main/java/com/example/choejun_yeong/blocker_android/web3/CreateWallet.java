package com.example.choejun_yeong.blocker_android.web3;

import android.os.Environment;
import android.widget.Toast;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class CreateWallet {
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

            return result;
        } catch (NoSuchAlgorithmException
                | NoSuchProviderException
                | InvalidAlgorithmParameterException
                | IOException
                | CipherException e) {
            e.printStackTrace();
            return null;
        }
    }

}
