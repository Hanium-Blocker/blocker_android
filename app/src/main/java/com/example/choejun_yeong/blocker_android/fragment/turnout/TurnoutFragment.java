package com.example.choejun_yeong.blocker_android.fragment.turnout;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.choejun_yeong.blocker_android.DataModel.CandidateVO;
import com.example.choejun_yeong.blocker_android.DataModel.ElectionVO;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.fragment.voting.adapter.ElectionSpinnerAdapter;
import com.example.choejun_yeong.blocker_android.util.ContractUtil;
import com.github.lzyzsd.circleprogress.DonutProgress;

import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static io.reactivex.internal.operators.flowable.FlowableBlockingSubscribe.subscribe;

public class TurnoutFragment extends Fragment {
    @NonNull
    private CompositeSubscription compositeSubscription;
    private List<ElectionVO> electionList;
    private List<CandidateVO> candidateList;
    private ElectionSpinnerAdapter spinnerAdapter;
    private int totalVoterCount = 10; // 유권자 총 수
    private int currentVotingCount = 0; // 현재 투표에 참여한 유권자 수
    ContractUtil contractUtil;
    int electionCounter = 0;
    int candidateCounter = 0;
    float percentageOfTurnout;

    @BindView(R.id.turnout_voter_spinner)
    Spinner spinner;

    @BindView(R.id.turnout_voter_donut_progress)
    DonutProgress donutProgress;

    @BindView(R.id.turnout_voter_text)
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_turnout_voter, container, false);
        ButterKnife.bind(this, view);


//        donutProgress.setProgress(20f);

        contractUtil = new ContractUtil(getContext());


        return view;
    }

    @OnClick(R.id.turnout_voter_button)
    void Onclick() {
//        int count= contractUtil.getElectionCount();
//        Log.d("@@@election info",""+contractUtil.getElectionInfo(contractUtil.getElectionCount()));
//        contractUtil.getElectionInfo(contractUtil.getElectionCount());
//        Log.d("@@@Count",""+count);

        // 선거 정보 리스트로 로딩.

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

        // 후보자 정보 리스트로 로딩.

        for (int i = 1; i < candidateCounter + 1; i++) {
            CandidateVO candidateVO = new CandidateVO();
            contractUtil.getCandidateInfo(i)
                    .subscribeOn(Schedulers.computation())
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
                    });
        }


    }

    @OnItemSelected(R.id.turnout_voter_spinner)
    void spinnerItemSelected(int position) {
        countingVoter(electionList.get(position).getElection_id());
        percentageOfTurnout = (float)currentVotingCount/(float)totalVoterCount*100f; //현재 투표율 퍼센테이지
        donutProgress.setProgress(percentageOfTurnout);
        textView.setText("온라인 선거 참여 신청자\n총 인원 "+totalVoterCount+"분 중, "+currentVotingCount+"분이 투표하셨습니다.");
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
        electionList = new ArrayList<>();
        candidateList = new ArrayList<>();

        compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(contractUtil.getElectionCount()
                .subscribeOn(Schedulers.computation())
                .onErrorReturn(new Func1<Throwable, BigInteger>() {
                    @Override
                    public BigInteger call(Throwable throwable) {
                        return BigInteger.valueOf(0);
                    }
                }).subscribe(x -> {
                    String str = x.toString();
                    electionCounter = Integer.parseInt(str);
                    Log.d("@@@@electionCount", str);
                }));

        compositeSubscription.add(contractUtil.getCandidateCount()
                .subscribeOn(Schedulers.computation())
                .onErrorReturn(new Func1<Throwable, BigInteger>() {
                    @Override
                    public BigInteger call(Throwable throwable) {
                        return BigInteger.valueOf(0);
                    }
                })
                .subscribe(x -> {
                    String str = x.toString();
                    candidateCounter = Integer.parseInt(str);
                    Log.d("@@@@candidateCount", str);
                }));
    }

    private void unBind() {

    }

    private void setViewofElectionList() {
        if (electionCounter == electionList.size()) { // 선거정보 카운터와 선거정보리스트의 크기가 같아질때 즉, 데이터 수신이 완료 된 시점에.
            spinnerAdapter = new ElectionSpinnerAdapter(getContext(),
                    android.R.layout.simple_spinner_item,
                    electionList);
            spinner.setAdapter(spinnerAdapter);

            countingVoter(electionList.get(0).getElection_id());
            percentageOfTurnout = (float)currentVotingCount/(float)totalVoterCount*100f; //현재 투표율 퍼센테이지
            Log.d("@@@",""+percentageOfTurnout);
            donutProgress.setProgress(percentageOfTurnout);
            textView.setText("온라인 선거 참여 신청자\n총 인원 "+totalVoterCount+"분 중, "+currentVotingCount+"분이 투표하셨습니다.");

        }
    }

    private void countingVoter(int election_id) {
        currentVotingCount = 0;
        for (int i = 0; i < candidateList.size(); i++) {
            if (candidateList.get(i).getElection_id() == election_id)
                currentVotingCount += candidateList.get(i).getVoteCount();
            Log.d("@@@cand vote count:",""+candidateList.get(i).getVoteCount());
        }
    }

    @OnClick (R.id.test_btn_turnout)
    void onButtonClicked(){
        for(int i=0;i<electionList.size();i++){
            Log.d("@@@@LOG",""+electionList.get(i).getElection_name()+"//"+electionList.get(i).getElection_id());
        }
    }
}
