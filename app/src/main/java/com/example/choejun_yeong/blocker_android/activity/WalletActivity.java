package com.example.choejun_yeong.blocker_android.activity;


import android.Manifest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.fragment.wallet.WalletFragment;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
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

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class WalletActivity extends AppCompatActivity {

    private final static String PRIVATE_KEY = "666a82fc33f8134577a7beb1bdeaa689bb72740178727691d63032432b83e0fb";

    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

    private final static String RECIPIENT = "0x2DcCa9B61E50D79A90a813fcD6a42c3A3Ac52e6f";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.login_container, new WalletFragment());
//        fragmentTransaction.commit();

        CheckPermission();

        // web3
//        Web3j web3j = Web3jFactory.build(new HttpService("https://ropsten.infura.io/v3/de770d2ce1834cc794cfd6dfe42fb83d"));
        Web3j web3j = Web3jFactory.build(new HttpService("http://10.0.2.2:8545")); //에뮬레이터의 로컬 주소는 10.0.2.2

        TransactionManager transactionManager = new RawTransactionManager(
                web3j,
                getCredentialsFromPrivateKey()
        );

        Transfer transfer = new Transfer(web3j, transactionManager);

        new Thread() {
            public void run() {
                try {
                    TransactionReceipt transactionReceipt = transfer.sendFunds(
                            RECIPIENT,
                            BigDecimal.ONE,
                            Convert.Unit.ETHER,
                            GAS_PRICE,
                            GAS_LIMIT
                    ).send();
                    Log.d("@@Transaction","/Transaction = " + transactionReceipt.getTransactionHash());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();



    }

    private void CheckPermission(){
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
        Log.d("ClientVersion: " ,ClientVersionString);
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
}



