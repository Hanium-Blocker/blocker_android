package com.example.choejun_yeong.blocker_android.fragment.candidate_info;

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
import com.example.choejun_yeong.blocker_android.DataModel.ElectionVO;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.fragment.candidate_info.adapter.CandidateAdapter;
import com.example.choejun_yeong.blocker_android.fragment.voting.adapter.ElectionSpinnerAdapter;
import com.example.choejun_yeong.blocker_android.service.CandidateService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CandidateMainFragment extends Fragment {
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    List<Candidate> candidateInfoList;
    RecyclerView rv;
    RecyclerView.LayoutManager rvManager;
    Spinner spinner;
    private ElectionSpinnerAdapter spinnerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_candidate_main, container, false);
        setupViews(view);

        return view;
    }

    private void setupViews(View view){
        spinner = view.findViewById(R.id.candidate_spinner);
        rv = view.findViewById(R.id.candidate_rv);
        rvManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(rvManager);

        //RecyclerView ViewPager처럼 바꿔주는 부분
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {

                mCompositeDisposable.add(CandidateService.getInstance().getCandidatelist(spinnerAdapter.getItem(position).getElection_id())
                        .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(CandidateMainFragment.this::getCandidates));
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

    private void getElections(@NonNull final List<ElectionVO> elections) {

        spinnerAdapter = new ElectionSpinnerAdapter(getContext(),
                android.R.layout.simple_spinner_item,
                elections);
        spinner.setAdapter(spinnerAdapter);

        mCompositeDisposable.add(CandidateService.getInstance().getCandidatelist(elections.get(0).getElection_id())
                .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getCandidates));

    }

    private void getCandidates(@NonNull final List<Candidate> candidates){
//        candidateInfoList = new ArrayList<Candidate>();
//        for(int i=0;i<candidates.size();i++){
//            Candidate candidateInfo = new Candidate();
//            candidateInfo.setName(candidates.get(i).getName());
//            candidateInfo.setNumber(candidates.get(i).getNumber());
//            candidateInfo.setParty(candidates.get(i).getParty());
//            candidateInfo.setCampaign_link(candidates.get(i).getCampaign_link());
//            candidateInfoList.add(candidateInfo);
//        }
        rv.setAdapter(new CandidateAdapter(candidates,getContext()));
    }
}
