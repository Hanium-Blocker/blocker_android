package com.example.choejun_yeong.blocker_android.fragment.admin_election;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.choejun_yeong.blocker_android.DataModel.Election;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.fragment.admin_election.adapter.ElectionManageRvAdapter;
import com.example.choejun_yeong.blocker_android.service.ElectionService;
import com.github.clans.fab.FloatingActionButton;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ElectionManageFragment extends Fragment {
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    private RecyclerView rv;
    private ElectionManageRvAdapter rvAdapter;
    private FloatingActionButton fab;
    private RecyclerView.LayoutManager rvManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_election, container, false);

        setupViews(view);

        return view;
    }

    private void setupViews(View v) {
        rv = v.findViewById(R.id.manage_election_rv);
        fab = v.findViewById(R.id.manage_election_fab);
        rvManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(rvManager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ElectionManageCreate dialfrag = ElectionManageCreate.newInstance();
                dialfrag.show(getActivity().getSupportFragmentManager(),"election create fragment");
            }
        });
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

        mCompositeDisposable.add(ElectionService.getInstance().getElectionlist()
        .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getElections));


    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void getElections(@NonNull final List<Election> elections) {
        rvAdapter = new ElectionManageRvAdapter(elections,getContext());
        rv.setAdapter(rvAdapter);

        Log.d("@@@@@","@@@"+elections.get(0).getElection_name());

    }




}
