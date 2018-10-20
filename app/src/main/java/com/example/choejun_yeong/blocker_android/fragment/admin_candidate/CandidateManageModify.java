package com.example.choejun_yeong.blocker_android.fragment.admin_candidate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.choejun_yeong.blocker_android.DataModel.AuthResponse;
import com.example.choejun_yeong.blocker_android.DataModel.Candidate;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.service.CandidateService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CandidateManageModify extends DialogFragment{
    @NonNull
    private CompositeDisposable mCompositeDisposable;
    private EditText candidate_name;
    private EditText candidate_num;
    private EditText candidate_party;
    private EditText candidate_birth;
    private EditText candidate_gender;
    private EditText candidate_campaignlink;
    private Fragment frag;
    private Candidate candidate;
    private int position;

    public static CandidateManageModify newInstance(Candidate candidate, CandidateManageFragment frag) {

        Bundle args = new Bundle();

        CandidateManageModify fragment = new CandidateManageModify();
        fragment.setArguments(args);
        fragment.frag= frag;
        fragment.candidate = candidate;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_admin_candidate_modify, null);

        candidate_name = view.findViewById(R.id.manage_candidate_create_name);
        candidate_num = view.findViewById(R.id.manage_candidate_create_num);
        candidate_party = view.findViewById(R.id.manage_candidate_create_party);
        candidate_birth = view.findViewById(R.id.manage_candidate_create_birth);
        candidate_gender = view.findViewById(R.id.manage_candidate_create_gender);
        candidate_campaignlink = view.findViewById(R.id.manage_candidate_create_campaignlink);

        builder.setView(view)
                .setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Candidate modifiedCandidate = new Candidate();
                        modifiedCandidate.setName(candidate_name.getText().toString());
                        modifiedCandidate.setNumber(Integer.valueOf(candidate_num.getText().toString()));
                        modifiedCandidate.setParty(candidate_party.getText().toString());
                        modifiedCandidate.setBirth(candidate_birth.getText().toString());
                        modifiedCandidate.setGender(candidate_gender.getText().toString());
                        modifiedCandidate.setCampaign_link(candidate_campaignlink.getText().toString());

                        mCompositeDisposable.add(CandidateService.getInstance().modifyCandidate(candidate.getElection_id(), candidate.getNumber(), modifiedCandidate)
                                .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableObserver<AuthResponse>() {
                                    @Override
                                    public void onNext(AuthResponse response) {
                                        if(response.getCode()==200){
                                            Reloadfrag();
                                        }
                                        else if(response.getCode()==409){

                                            Toast.makeText(getContext(), "후보자 중복 오류", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.d("@@@","Test2");
                                        Toast.makeText(getContext(), "후보자 수정 오류", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onComplete() {
                                        Log.d("@@@","Test3");
                                        Toast.makeText(getContext(), "후보자 수정 성공", Toast.LENGTH_SHORT).show();
                                    }
                                }));

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        return builder.create();

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

    private void Reloadfrag() {
        getFragmentManager().beginTransaction()
                .detach(frag)
                .attach(frag)
                .commit();
    }
}
