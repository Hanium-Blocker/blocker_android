package com.example.choejun_yeong.blocker_android.activity;


import android.Manifest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.contracts.Election;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalletActivity extends AppCompatActivity {

    @BindView(R.id.vote_button1)
    Button btn_vote;

    private final static String PRIVATE_KEY = "666A82FC33F8134577A7BEB1BDEAA689BB72740178727691D63032432B83E0FB";

    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

    private final static String RECIPIENT = "0x2DcCa9B61E50D79A90a813fcD6a42c3A3Ac52e6f";

    private final static String CONTRACT_ADDRESS = "0x5403b5705c2a89517e03ea220806c818796ba3ff";

    Web3j web3j;
    Credentials credentials;
    Election election;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        ButterKnife.bind(this);

        CheckPermission();

//        web3j = Web3jFactory.build(new HttpService("http://10.0.2.2:8545")); //에뮬레이터의 로컬 주소는 10.0.2.2
        web3j = Web3jFactory.build(new HttpService("https://ropsten.infura.io/v3/de770d2ce1834cc794cfd6dfe42fb83d"));//해당 컨트렉트 주소로 연결
        credentials = getCredentialsFromPrivateKey(); //개인키를 통한 자격 획득.
        election = loadContract(CONTRACT_ADDRESS, web3j, credentials); //컨트랙트 주소, web3, 자격을 통한 컨트렉트 로딩.

        Credentials myCredential = null;
        try {
            myCredential = WalletUtils.loadCredentials("123","/storage/emulated/0/Download/UTC--2018-10-24T18-55-17.507--730ef7fff4f05f06cdc7947a9fae2a6f2b630675.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }

        Log.d("@@@Address: ",""+myCredential.getAddress());


//        new Thread() {
//            public void run() {
//                try {
//                    Log.d("@@@Log",""+election.candidates(BigInteger.valueOf(1l)).send().toString());
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//
    }

    private void CheckPermission() {
        //Permission Check
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // Toast.makeText(MainActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // Toast.makeText(MainActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("모임 연결을 위해선 권한이 필요합니다.")
                .setDeniedMessage("왜 거부하셨어요...\n하지만 [설정] > [권한] 에서 권한을 허용할 수 있어요!")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }


    private void printWeb3Version(Web3j web3j) {
        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String ClientVersionString = web3ClientVersion.getWeb3ClientVersion();
        Log.d("ClientVersion: ", ClientVersionString);
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

    private void transferEthereum(Web3j web3j, Credentials credentials) throws Exception {
        TransactionManager transactionManager = new RawTransactionManager(
                web3j,
                credentials
        );

        Transfer transfer = new Transfer(web3j, transactionManager);

        TransactionReceipt transactionReceipt = transfer.sendFunds(
                RECIPIENT,
                BigDecimal.ONE,
                Convert.Unit.ETHER,
                GAS_PRICE,
                GAS_LIMIT
        ).send();

        System.out.print("Transaction = " + transactionReceipt.getTransactionHash());
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
            Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show();
            return result;

        } catch (NoSuchAlgorithmException
                | NoSuchProviderException
                | InvalidAlgorithmParameterException
                | IOException
                | CipherException e) {
            e.printStackTrace();
            Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show();
            return null;
        }
    }



    ////////////////////////////////////////////////////////// click event

    @OnClick(R.id.vote_button1)
    void onButtonClicked() {
        new Thread() {
            public void run() {
                try {

                    election.vote(BigInteger.valueOf(1l)).send();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @OnClick(R.id.vote_button2)
    void onButtonClicked2() {

        new AsyncTask<Void, Void, String>() {
            //            String str =
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    Log.d("@@@", "이름: " + election.candidates(BigInteger.valueOf(1l)).send().getValue2()
                            + "// 투표수: " + election.candidates(BigInteger.valueOf(1l)).send().getValue3()
                            + "이름: " + election.candidates(BigInteger.valueOf(2l)).send().getValue2()
                            + "// 투표수: " + election.candidates(BigInteger.valueOf(2l)).send().getValue3());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

        }.execute();

    }

    @OnClick(R.id.make_wallet_btn)
    void onButtonClicked3(){
        String[] wallet = createWallet("123");
        Log.d("@@@wallet: ",""+wallet[0]+wallet[1]);
    }

}



