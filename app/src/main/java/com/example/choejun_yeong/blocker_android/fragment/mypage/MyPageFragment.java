package com.example.choejun_yeong.blocker_android.fragment.mypage;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.util.ContractUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

public class MyPageFragment extends Fragment {
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @BindView(R.id.name_text)
    TextView nameTxt;

    @BindView(R.id.balance_text)
    TextView balanceTxt;

    @BindView(R.id.check_private_key_btn)
    Button checkPrivate;

    private CheckPrivateKeyDialog dialog;

    private ContractUtil contractUtil;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        ButterKnife.bind(this, view);

        contractUtil = new ContractUtil(getContext());

        balanceTxt.setText(contractUtil.getBalance()+"eth 입니다.");

        return view;
    }


    @OnClick(R.id.check_private_key_btn)
    public void checkPrivateKey(){
        Log.d("@@@test","test");
        dialog = new CheckPrivateKeyDialog(getContext(), contractUtil.getPrivateKey());
        dialog.show();
    }



}
