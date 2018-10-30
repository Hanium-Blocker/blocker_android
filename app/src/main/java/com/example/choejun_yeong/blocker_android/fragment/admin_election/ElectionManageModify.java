package com.example.choejun_yeong.blocker_android.fragment.admin_election;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.choejun_yeong.blocker_android.DataModel.AuthResponse;
import com.example.choejun_yeong.blocker_android.DataModel.ElectionVO;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.service.ElectionService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ElectionManageModify extends DialogFragment {
    @NonNull
    private CompositeDisposable mCompositeDisposable;
    private EditText name;
    private Fragment frag;
    ElectionVO election;

    public static ElectionManageModify newInstance(ElectionManageFragment frag,ElectionVO election) {

        Bundle args = new Bundle();

        ElectionManageModify fragment = new ElectionManageModify();
        fragment.setArguments(args);
        fragment.frag= frag;
        fragment.election = election;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_admin_election_modify, null);

        name = view.findViewById(R.id.manage_election_modify_name);
        name.setText(election.getElection_name());

        builder.setView(view)
                .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ElectionVO elec = new ElectionVO();
                        elec.setElection_name(name.getText().toString());
                        mCompositeDisposable.add(ElectionService.getInstance().modifyElection(election.getElection_id(),elec)
                                .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableObserver<AuthResponse>() {
                                    @Override
                                    public void onNext(AuthResponse response) {
                                        if(response.getCode()==200){
                                            Reloadfrag();
                                        }
                                        else if(response.getCode()==409){

                                            Toast.makeText(getContext(), "선거 유형 중복 오류", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Toast.makeText(getContext(), "선거 수정 오류", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onComplete() {
                                        Toast.makeText(getContext(), "선거 수정 완료", Toast.LENGTH_SHORT).show();
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
