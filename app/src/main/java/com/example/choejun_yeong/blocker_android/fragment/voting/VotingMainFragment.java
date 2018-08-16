package com.example.choejun_yeong.blocker_android.fragment.voting;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.choejun_yeong.blocker_android.DataModel.Candidate;
import com.example.choejun_yeong.blocker_android.DataModel.Candidate_Voting;
import com.example.choejun_yeong.blocker_android.DataModel.Election;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.fragment.voting.adapter.ElectionSpinnerAdapter;
import com.example.choejun_yeong.blocker_android.fragment.voting.adapter.Voting_cand_rv_adapter;
import com.example.choejun_yeong.blocker_android.service.CandidateService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class VotingMainFragment extends Fragment {
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    List<Candidate_Voting> modellist;
    RecyclerView rv;
    Spinner spinner;
    Button voting_btn;
    RecyclerView.LayoutManager rvManager;
    private ElectionSpinnerAdapter spinnerAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voting_main, container, false);

        setupViews(view);
        return view;
    }

    private void setupViews(View v) {
        rv = v.findViewById(R.id.voting_rv);
        spinner = v.findViewById(R.id.voting_spinner);
        voting_btn = v.findViewById(R.id.voting_btn);
        rvManager = new LinearLayoutManager(getContext());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {

                mCompositeDisposable.add(CandidateService.getInstance().getCandidatelist(spinnerAdapter.getItem(position).getElection_id())
                        .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(VotingMainFragment.this::getCandidates));
                // Here you get the current item (a User object) that is selected by its position

                // Here you can do the action you want to...
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });

        rv.setLayoutManager(rvManager);

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

        mCompositeDisposable.add(CandidateService.getInstance().getElectionlist()
        .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getElections));
    }


    private void getElections(@NonNull final List<Election> elections) {

//        Log.d("@@@@",elections.get(0).getElection_name());
        spinnerAdapter = new ElectionSpinnerAdapter(getContext(),
                android.R.layout.simple_spinner_item,
                elections);
        spinner.setAdapter(spinnerAdapter);

        mCompositeDisposable.add(CandidateService.getInstance().getCandidatelist(elections.get(0).getElection_id())
                .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getCandidates));

    }

    private void getCandidates(@NonNull final List<Candidate> candidates){
        modellist = new ArrayList<>();
        for(int i=0;i<candidates.size();i++){
            Candidate_Voting cand = new Candidate_Voting();
            cand.setName(candidates.get(i).getName());
            cand.setId(candidates.get(i).getNumber());
            modellist.add(cand);
        }
        rv.setAdapter(new Voting_cand_rv_adapter(modellist,getContext()));
    }

    private void unBind() {
        mCompositeDisposable.clear();
    }


}