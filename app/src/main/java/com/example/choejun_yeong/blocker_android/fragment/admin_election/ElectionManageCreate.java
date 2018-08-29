package com.example.choejun_yeong.blocker_android.fragment.admin_election;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.choejun_yeong.blocker_android.R;

import io.reactivex.disposables.CompositeDisposable;

public class ElectionManageCreate extends DialogFragment {
    @NonNull
    private CompositeDisposable mCompositeDisposable;
    private EditText name;

    public static ElectionManageCreate newInstance() {

        Bundle args = new Bundle();

        ElectionManageCreate fragment = new ElectionManageCreate();
        fragment.setArguments(args);
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
}
