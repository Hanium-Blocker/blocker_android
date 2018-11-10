package com.example.choejun_yeong.blocker_android.fragment.turnout;

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
import android.widget.Spinner;

import com.example.choejun_yeong.blocker_android.DataModel.CandidateVO;
import com.example.choejun_yeong.blocker_android.DataModel.ElectionVO;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.fragment.turnout.adapter.ScoreListAdapter;
import com.example.choejun_yeong.blocker_android.fragment.voting.adapter.ElectionSpinnerAdapter;
import com.example.choejun_yeong.blocker_android.util.ContractUtil;

import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import io.reactivex.disposables.CompositeDisposable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ScoreFragment extends Fragment {
    @NonNull
    private CompositeSubscription compositeSubscription;
    private RecyclerView.LayoutManager rvManager;
    private List<ElectionVO> electionList;
    private List<CandidateVO> candidateList;
    private int totalVoterCount = 10; // 유권자 총 수
    private int currentVotingCount = 0; // 현재 투표에 참여한 유권자 수
    private int electionCounter = 0;
    private int candidateCounter = 0;
    private int currentElectionptr = 0;
    private List<CandidateVO> currentCandlist;
    private ElectionSpinnerAdapter spinnerAdapter;

    ContractUtil contractUtil;

    @BindView(R.id.turnout_score_spinner)
    Spinner spinner;

    @BindView(R.id.turnout_score_rv)
    RecyclerView rv;

    @BindView(R.id.turnout_score_testbtn)
    Button button;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_turnout_score, container, false);
        ButterKnife.bind(this,view);

        contractUtil = new ContractUtil(getContext());
        rvManager = new LinearLayoutManager(getContext());

        return view;
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

    private void bind(){
        compositeSubscription = new CompositeSubscription();

        electionList = new ArrayList<>();
        candidateList = new ArrayList<>();


        compositeSubscription.add(contractUtil.getElectionCount()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
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




    }

    private void unBind(){
        compositeSubscription.clear();
    }

    private void getCandidatesList(){
        for (int i = 1; i < candidateCounter + 1; i++) {
            CandidateVO candidateVO = new CandidateVO();
            contractUtil.getCandidateInfo(i)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
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
                    .subscribeOn(Schedulers.computation())
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
            rv.setAdapter(new ScoreListAdapter(currentCandlist,getContext(),this));
        }
    }

    @OnClick(R.id.turnout_score_testbtn)
    void onButtonClicked(){
//        contractUtil.isVoted().subscribeOn(Schedulers.computation())
//                .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
//                .onErrorReturn(new Func1<Throwable, Boolean>() {
//                    @Override
//                    public Boolean call(Throwable throwable) {
//                        Log.d("@@@ERROR3", throwable.getMessage());
//                        return null;
//                    }
//                })
//                .subscribe(x -> {
//                    Log.d("@@@@is voted? ",""+x.toString());
//                });
    }

    @OnItemSelected(R.id.turnout_score_spinner)
    void spinnerItemSelected(int position) {
        currentElectionptr = position;
        setCandidateItems(candidateList,currentElectionptr);
    }

    public int getTotalVoterCount() {
        return totalVoterCount;
    }
}
