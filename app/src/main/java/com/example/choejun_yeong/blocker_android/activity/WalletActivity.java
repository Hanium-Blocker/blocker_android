package com.example.choejun_yeong.blocker_android.activity;


import android.Manifest;
import android.os.AsyncTask;
import android.os.Bundle;
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

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
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

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.login_container, new WalletFragment());
//        fragmentTransaction.commit();

        CheckPermission();

//        web3j = Web3jFactory.build(new HttpService("http://10.0.2.2:8545")); //에뮬레이터의 로컬 주소는 10.0.2.2
        web3j = Web3jFactory.build(new HttpService("https://ropsten.infura.io/v3/de770d2ce1834cc794cfd6dfe42fb83d"));
        credentials = getCredentialsFromPrivateKey();
//
        election = loadContract(CONTRACT_ADDRESS, web3j, credentials);


//
        new Thread() {
            public void run() {
                try {
//                    election.vote(BigInteger.valueOf(1l)).send();
//                    Log.d("@@voteCount = ",election.candidatesCount().send().toString());
//                    Log.d("@@@candidate_info", "이름: " + election.candidates(BigInteger.valueOf(1l)).send().getValue2() + "// 투표수: " + election.candidates(BigInteger.valueOf(1l)).send().getValue3());
                    printWeb3Version(web3j);
                    Log.d("@@@Log",""+election.candidates(BigInteger.valueOf(1l)).send().toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
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

//    private void voteElection(Election election) throws Exception {
//        election
//                .vote(BigInteger.valueOf(1L))
//                .send();
//        addressBook
//                .addAddress("0x256a04B9F02036Ed2f785D8f316806411D605285", "Tom")
//                .send();
//
//        addressBook
//                .addAddress("0x82CDf5a3192f2930726637e9C738A78689a91Be3", "Susan")
//                .send();
//
//        addressBook
//                .addAddress("0x95F57F1DD015ddE7Ec2CbC8212D0ae2faC9acA11", "Bob")
//                .send();
//    }
//
//    private void printAddresses(AddressBook addressBook) throws Exception {
//        for (Object address : addressBook.getAddresses().send()) {
//            String addressString = address.toString();
//            String alias = addressBook.getAlias(addressString).send();
//            System.out.println("Address " + addressString + " aliased as " + alias);
//        }
//    }
//
//    private void removeAddress(AddressBook addressBook) throws Exception {
//        addressBook
//                .removeAddress("0x256a04B9F02036Ed2f785D8f316806411D605285")
//                .send();
//    }

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

}



