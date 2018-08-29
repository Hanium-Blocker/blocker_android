package com.example.choejun_yeong.blocker_android.fragment.login;

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
import com.example.choejun_yeong.blocker_android.service.AuthService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SignUpFragment extends Fragment {

    Fragment frag;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    EditText id,pw,name,gender,tel,region,birth;
    Button signup_btn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        frag = this;
        View view = inflater.inflate(R.layout.fragment_signup,container,false);
        setupViews(view);
        return view;

    }

    private void setupViews(View v) {

        id = v.findViewById(R.id.signup_id);
        pw = v.findViewById(R.id.signup_pw);
        name = v.findViewById(R.id.signup_name);
        gender = v.findViewById(R.id.signup_gender);
        birth = v.findViewById(R.id.signup_birth);
        tel = v.findViewById(R.id.signup_tel);
        region = v.findViewById(R.id.signup_region);
        signup_btn = v.findViewById(R.id.signup_btn);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo userInfo = new UserInfo();
                userInfo.setAuth_id(id.getText().toString());
                userInfo.setPassword(pw.getText().toString());
                userInfo.setName(name.getText().toString());
                userInfo.setGender(gender.getText().toString());
                userInfo.setBirth(birth.getText().toString());
                userInfo.setTel(tel.getText().toString());
                userInfo.setRegion(region.getText().toString());
                userInfo.setAdmin_state(0);

                mCompositeDisposable.add(AuthService.getInstance().signUp(userInfo)
                        .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(SignUpFragment.this::signup));


            }
        });
    }


    private void signup(@NonNull final AuthResponse response) {
        if(response.getCode()==200){
            Toast.makeText(getActivity(), "회원가입 성공", Toast.LENGTH_SHORT).show();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().remove(SignUpFragment.this).commit();
            fragmentManager.popBackStack();
        }
        else if(response.getCode()==409){
            Toast.makeText(getActivity(), "중복된 아이디 입니다.", Toast.LENGTH_SHORT).show();
        }
        else{
            Log.d("@@@@","오류!!");
        }
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
