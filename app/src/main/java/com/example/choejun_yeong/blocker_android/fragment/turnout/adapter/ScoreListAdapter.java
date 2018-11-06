package com.example.choejun_yeong.blocker_android.fragment.turnout.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.choejun_yeong.blocker_android.DataModel.CandidateVO;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.fragment.turnout.ScoreFragment;

import java.util.List;

public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListViewHolder> {
    private List<CandidateVO> list;
    private Context context;
    private ScoreFragment fragment;

    public ScoreListAdapter(List<CandidateVO> list, Context context, ScoreFragment fragment) {
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ScoreListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_turnout_score_rv_item, viewGroup, false);
        ScoreListViewHolder holder = new ScoreListViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreListViewHolder scoreListViewHolder, int i) {
        Log.d("@@LOGLOG",""+list.get(i).getName().toString());
        scoreListViewHolder.textView.setText(list.get(i).getName().toString());
        scoreListViewHolder.progressBar.setMax(fragment.getTotalVoterCount());
        scoreListViewHolder.progressBar.setProgress(list.get(i).getVoteCount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
