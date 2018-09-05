package com.example.choejun_yeong.blocker_android.fragment.admin_candidate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.choejun_yeong.blocker_android.DataModel.Candidate;
import com.example.choejun_yeong.blocker_android.DataModel.Election;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.fragment.admin_candidate.adapter.CandidateManageAdapter;
import com.example.choejun_yeong.blocker_android.fragment.candidate_info.CandidateMainFragment;
import com.example.choejun_yeong.blocker_android.fragment.candidate_info.adapter.CandidateAdapter;
import com.example.choejun_yeong.blocker_android.fragment.voting.adapter.ElectionSpinnerAdapter;
import com.example.choejun_yeong.blocker_android.service.CandidateService;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CandidateManageFragment extends Fragment{
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    private List<Candidate> candidateInfoList;
    private RecyclerView rv;
    private RecyclerView.LayoutManager rvManager;
    private Spinner spinner;
    private FloatingActionButton fab;
    private ElectionSpinnerAdapter spinnerAdapter;
    private CandidateManageFragment fragment;
    private int index;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_candidate, container, false);
        fragment = this;
        setupViews(view);

        return view;
    }

    private void setupViews(View view){
        spinner = view.findViewById(R.id.admin_candidate_spinner);
        fab = view.findViewById(R.id.admin_candidate_fab);
        rv = view.findViewById(R.id.admin_candidate_rv);
        rvManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(rvManager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CandidateManageCreate dialfrag = CandidateManageCreate.newInstance(index, fragment);
                dialfrag.show(getActivity().getSupportFragmentManager(),"candidate create fragment");
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {

                mCompositeDisposable.add(CandidateService.getInstance().getCandidatelist(spinnerAdapter.getItem(position).getElection_id())
                        .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(CandidateManageFragment.this::getCandidates));

                //후보자 추가 시 election_id를 알기위해 index변수에 저장.
                index = spinnerAdapter.getItem(position).getElection_id();

                // Here you get the current item (a User object) that is selected by its position

                // Here you can do the action you want to...
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
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

        mCompositeDisposable.add(CandidateService.getInstance().getElectionlist()
                .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getElections));
    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void getElections(@NonNull final List<Election> elections) {

        spinnerAdapter = new ElectionSpinnerAdapter(getContext(),
                android.R.layout.simple_spinner_item,
                elections);
        spinner.setAdapter(spinnerAdapter);

        mCompositeDisposable.add(CandidateService.getInstance().getCandidatelist(elections.get(0).getElection_id())
                .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getCandidates));

    }

    private void getCandidates(@NonNull final List<Candidate> candidates){
        candidateInfoList = new ArrayList<Candidate>();
        for(int i=0;i<candidates.size();i++){
            Candidate candidateInfo = new Candidate();
            candidateInfo.setElection_id(candidates.get(i).getElection_id());
            candidateInfo.setName(candidates.get(i).getName());
            candidateInfo.setNumber(candidates.get(i).getNumber());
            candidateInfo.setParty(candidates.get(i).getParty());
            candidateInfo.setCampaign_link(candidates.get(i).getCampaign_link());
            candidateInfoList.add(candidateInfo);
        }
        rv.setAdapter(new CandidateManageAdapter(candidateInfoList, fragment));
    }


}
