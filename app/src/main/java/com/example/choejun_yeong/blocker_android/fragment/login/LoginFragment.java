package com.example.choejun_yeong.blocker_android.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.choejun_yeong.blocker_android.DataModel.AuthResponse;
import com.example.choejun_yeong.blocker_android.DataModel.UserInfo;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.activity.AdminActivity;
import com.example.choejun_yeong.blocker_android.activity.LoginActivity;
import com.example.choejun_yeong.blocker_android.activity.MainActivity;
import com.example.choejun_yeong.blocker_android.service.AuthService;
import com.example.choejun_yeong.blocker_android.service.CandidateService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginFragment extends Fragment {


    @NonNull
    private CompositeDisposable mCompositeDisposable;

    EditText id,pw;
    Button login_btn,signup_btn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);
        setupViews(view);
        return view;

    }

    private void setupViews(View v) {
        id=v.findViewById(R.id.login_id);
        pw=v.findViewById(R.id.login_pw);
        login_btn = v.findViewById(R.id.login_btn);
        signup_btn = v.findViewById(R.id.login_signup_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo userInfo = new UserInfo();
                userInfo.setAuth_id(id.getText().toString());
                userInfo.setPassword(pw.getText().toString());

                mCompositeDisposable.add(AuthService.getInstance().login(userInfo)
                        .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<AuthResponse>() {
                            @Override
                            public void onNext(AuthResponse response) {
                                login(response);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getContext(), "아이디 또는 패스워드 오류", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {

                            }
                        }));

            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.login_container, new SignUpFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
    }

    private void login(@NonNull final AuthResponse response) {
//            if (response.getCode() == 200) {
                Toast.makeText(getContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                if (response.getMessage().equals("GUEST")) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    getActivity().finish();
                    startActivity(intent);
                } else if (response.getMessage().equals("ADMIN")) {
                    Intent intent = new Intent(getActivity(), AdminActivity.class);
                    getActivity().finish();
                    startActivity(intent);
                }
//            } else if (response.getCode() == 403) {

//            }

        //TODO : 관리자 권한 로그인 시 관리자 페이지로의 전환 코드 구현.
//        Intent intent = new Intent(getActivity(), AdminActivity.class);
//        getActivity().finish();
//        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        bind();
    }

    @Override
    public void onPause() {
        super.onPause();
        unBind();
    }

    private void bind() {
        mCompositeDisposable = new CompositeDisposable();

    }

    private void unBind() {
        mCompositeDisposable.clear();
    }


}
