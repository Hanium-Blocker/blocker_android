package com.example.choejun_yeong.blocker_android.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.choejun_yeong.blocker_android.contracts.Election;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;

public class ContractUtil {

    private final static String PRIVATE_KEY = "666A82FC33F8134577A7BEB1BDEAA689BB72740178727691D63032432B83E0FB";
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    private final static String RECIPIENT = "0x2DcCa9B61E50D79A90a813fcD6a42c3A3Ac52e6f";
    private final static String CONTRACT_ADDRESS = "0x5403b5705c2a89517e03ea220806c818796ba3ff";
    private Context context;
    Web3j web3j;
    Credentials credentials;
    Election election;

    public ContractUtil(Context context) {
        this.context=context;
        web3j = Web3jFactory.build(new HttpService("https://ropsten.infura.io/v3/de770d2ce1834cc794cfd6dfe42fb83d"));//해당 컨트렉트 주소로 연결
        credentials = getCredentialsFromPrivateKey(); //개인키를 통한 자격 획득.
        election = loadContract(CONTRACT_ADDRESS, web3j, credentials); //컨트랙트 주소, web3, 자격을 통한 컨트렉트 로딩.
    }

    private Credentials getCredentialsFromWallet() throws IOException, CipherException {
        return WalletUtils.loadCredentials(
                "passphrase",
                "wallet/path"
        );
    }

    private Credentials getCredentialsFromPrivateKey() {
        return Credentials.create(PRIVATE_KEY);
    }

    private String deployContract(Web3j web3j, Credentials credentials) throws Exception {
        return Election.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT)
                .send()
                .getContractAddress();
    }

    private Election loadContract(String contractAddress, Web3j web3j, Credentials credentials) {
        return Election.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);
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
            Toast.makeText(context, "해당 계정의 지갑이 생성되었습니다.", Toast.LENGTH_SHORT).show();
            return result;

        } catch (NoSuchAlgorithmException
                | NoSuchProviderException
                | InvalidAlgorithmParameterException
                | IOException
                | CipherException e) {
            e.printStackTrace();
            Toast.makeText(context, "해당 계정의 지갑 생성에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public void getvoteCount(){
        RemoteCall<BigInteger> remoteCall = election.getvoteCount(BigInteger.valueOf(1));

        try {
            Log.d("@@@",""+remoteCall.sendAsync().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

     // TODO:  잔액 조회 기능 만들기.

}
