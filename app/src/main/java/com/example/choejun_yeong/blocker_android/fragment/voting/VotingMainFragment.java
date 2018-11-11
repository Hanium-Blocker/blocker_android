package com.example.choejun_yeong.blocker_android.fragment.voting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.choejun_yeong.blocker_android.DataModel.Candidate;
import com.example.choejun_yeong.blocker_android.DataModel.CandidateVO;
import com.example.choejun_yeong.blocker_android.DataModel.Candidate_Voting;
import com.example.choejun_yeong.blocker_android.DataModel.ElectionVO;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.activity.MainActivity;
import com.example.choejun_yeong.blocker_android.fragment.turnout.adapter.ScoreListAdapter;
import com.example.choejun_yeong.blocker_android.fragment.voting.adapter.ElectionSpinnerAdapter;
import com.example.choejun_yeong.blocker_android.fragment.voting.adapter.Voting_cand_rv_adapter;
import com.example.choejun_yeong.blocker_android.service.CandidateService;
import com.example.choejun_yeong.blocker_android.util.ContractUtil;

import org.spongycastle.pqc.jcajce.provider.BouncyCastlePQCProvider;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

public class VotingMainFragment extends Fragment {
    @NonNull
    private CompositeDisposable mCompositeDisposable;
    private CompositeSubscription compositeSubscription;
//    List<Candidate_Voting> modellist;
    private List<CandidateVO> candidateList;
    private List<ElectionVO> electionList;
    private int electionCounter = 0;
    private int candidateCounter = 0;
    private int currentElectionptr = 0;
    private List<CandidateVO> currentCandlist;
    RecyclerView rv;
    Voting_cand_rv_adapter rvAdapter;
    Spinner spinner;
    Button voting_btn;
    TextView voting_votedText;
    ContractUtil contractUtil;
    RecyclerView.LayoutManager rvManager;
    private ElectionSpinnerAdapter spinnerAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voting_main, container, false);

        contractUtil = new ContractUtil(getContext());

        setupViews(view);
        return view;
    }

    private void setupViews(View v) {
        MainActivity.tabVisible(View.VISIBLE);

        rv = v.findViewById(R.id.voting_rv);
        spinner = v.findViewById(R.id.voting_spinner);
        voting_btn = v.findViewById(R.id.voting_btn);
        rvManager = new LinearLayoutManager(getContext());
        voting_votedText = v.findViewById(R.id.voting_votedtext);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                currentElectionptr = position;
                setCandidateItems(candidateList,currentElectionptr);
                voting_btn.setEnabled(false);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });

        rv.setLayoutManager(rvManager);

        voting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("@@@LOGTEST",""+rvAdapter.getSelectedItem().getName());
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.voting_container, FingerprintFragment.newInstance(rvAdapter.getSelectedItem().getCandidateId()));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                MainActivity.tabVisible(View.GONE);
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
        compositeSubscription = new CompositeSubscription();


        electionList = new ArrayList<>();
        candidateList = new ArrayList<>();


        compositeSubscription.add(contractUtil.getElectionCount()
                .subscribeOn(rx.schedulers.Schedulers.computation())
                .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .onErrorReturn(new Func1<Throwable, BigInteger>() {
                    @Override
                    public BigInteger call(Throwable throwable) {
                        return BigInteger.valueOf(0);
                    }
                }).subscribe(x -> {
                    String str = x.toString();
                    electionCounter = Integer.parseInt(str);
                    getElectionsList();

                    compositeSubscription.add(contractUtil.getCandidateCount()
                            .subscribeOn(rx.schedulers.Schedulers.computation())
                            .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                            .onErrorReturn(new Func1<Throwable, BigInteger>() {
                                @Override
                                public BigInteger call(Throwable throwable) {
                                    return BigInteger.valueOf(0);
                                }
                            })
                            .subscribe(y -> {
                                String str2 = y.toString();
                                candidateCounter = Integer.parseInt(str2);
                                getCandidatesList();
                            }));
                }));

        //TODO: 투표 여부 검사
        compositeSubscription.add(contractUtil.isVoted()
                .subscribeOn(rx.schedulers.Schedulers.computation())
                .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .onErrorReturn(new Func1<Throwable, Tuple2<BigInteger,Boolean>>() {
                    @Override
                    public Tuple2<BigInteger,Boolean> call(Throwable throwable) {
                        Log.d("@@@@Error in votingMain","error");
                        return null;
                    }
                }).subscribe(this::hasVoted));


    }

    private void unBind() {
        mCompositeDisposable.clear();
        compositeSubscription.clear();
    }

    private void getCandidatesList(){
        for (int i = 1; i < candidateCounter + 1; i++) {
            CandidateVO candidateVO = new CandidateVO();
            contractUtil.getCandidateInfo(i)
                    .subscribeOn(rx.schedulers.Schedulers.computation())
                    .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                    .onErrorReturn(new Func1<Throwable, Tuple4<BigInteger, String, BigInteger, BigInteger>>() {
                        @Override
                        public Tuple4<BigInteger, String, BigInteger, BigInteger> call(Throwable throwable) {
                            Log.d("@@@ERROR2", "error!");
                            return null;
                        }
                    })
                    .subscribe(x -> {
                        candidateVO.setCandidateId(Integer.parseInt(x.getValue1().toString()));
                        candidateVO.setName(x.getValue2());
                        candidateVO.setVoteCount(Integer.parseInt(x.getValue3().toString()));
                        candidateVO.setElection_id(Integer.parseInt(x.getValue4().toString()));
                        candidateList.add(candidateVO);
                        Log.d("@@@List", "///list:" + candidateList.size());
                        setCandidateItems(candidateList,currentElectionptr);
                    });
        }
    }

    private void getElectionsList(){
        for (int i = 1; i < electionCounter + 1; i++) {
            ElectionVO electionVO = new ElectionVO();
            contractUtil.getElectionInfo(i)
                    .subscribeOn(rx.schedulers.Schedulers.computation())
                    .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                    .onErrorReturn(new Func1<Throwable, Tuple2<BigInteger, String>>() {
                        @Override
                        public Tuple2 call(Throwable throwable) {
                            Log.d("@@@ERROR", throwable.getMessage());
                            return null;
                        }
                    })
                    .subscribe(x -> {
                        electionVO.setElection_id(Integer.parseInt(x.getValue1().toString()));
                        electionVO.setElection_name(x.getValue2());
                        electionList.add(electionVO);
                        Log.d("@@@Election_name:",x.getValue2());
                        setViewofElectionList();
                    });
        }
    }

    private void setViewofElectionList() {
        if (electionCounter == electionList.size()) { // 선거정보 카운터와 선거정보리스트의 크기가 같아질때 즉, 데이터 수신이 완료 된 시점에.
            spinnerAdapter = new ElectionSpinnerAdapter(getContext(),
                    android.R.layout.simple_spinner_item,
                    electionList);
            spinner.setAdapter(spinnerAdapter);
        }
    }

    private void setCandidateItems(List<CandidateVO> list,int election_ptr){

        if(candidateCounter == candidateList.size()){
            currentCandlist = new ArrayList<>();
            for(int i=0;i<candidateList.size();i++){
                if(candidateList.get(i).getElection_id()==electionList.get(election_ptr).getElection_id()){
                    currentCandlist.add(candidateList.get(i));
                }
            }
            rv.setLayoutManager(rvManager);
            rvAdapter = new Voting_cand_rv_adapter(currentCandlist,getContext(),voting_btn);
            rv.setAdapter(rvAdapter);
        }
    }


    private void hasVoted(Tuple2<BigInteger,Boolean> tuple2){
        if(tuple2.getValue2().equals(true)){
            voting_btn.setVisibility(View.GONE);
            voting_votedText.setVisibility(View.VISIBLE);
        }
        else{
            voting_btn.setVisibility(View.VISIBLE);
            voting_votedText.setVisibility(View.GONE);
        }
    }

}