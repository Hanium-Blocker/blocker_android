package com.example.choejun_yeong.blocker_android.fragment.admin_election;

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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.choejun_yeong.blocker_android.DataModel.AuthResponse;
import com.example.choejun_yeong.blocker_android.DataModel.Election;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.service.ElectionService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ElectionManageCreate extends DialogFragment {
    @NonNull
    private CompositeDisposable mCompositeDisposable;
    private EditText name;
    private Fragment frag;

    public static ElectionManageCreate newInstance(ElectionManageFragment frag) {

        Bundle args = new Bundle();

        ElectionManageCreate fragment = new ElectionManageCreate();
        fragment.setArguments(args);
        fragment.frag= frag;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_admin_election_create, null);

        name = view.findViewById(R.id.manage_election_create_name);


        builder.setView(view)
                .setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Election election = new Election();
                        election.setElection_name(name.getText().toString());

                        mCompositeDisposable.add(ElectionService.getInstance().addElection(election)
                                .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableObserver<AuthResponse>() {
                                    @Override
                                    public void onNext(AuthResponse response) {
                                        Log.d("@@@","Test");
                                        if(response.getCode()==200){
                                            Reloadfrag();
                                        }
                                        else if(response.getCode()==409){

                                            Toast.makeText(getContext(), "선거 유형 중복 오류", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.d("@@@","Test");
                                        Toast.makeText(getContext(), "선거 만들기 오류", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onComplete() {
                                        Log.d("@@@","Test");
                                        Toast.makeText(getContext(), "선거 만들기 성공", Toast.LENGTH_SHORT).show();
                                    }
                                }));

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("@@@@","@@@@@@@@"+name.getText().toString());
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
