package com.example.choejun_yeong.blocker_android.fragment.turnout.adapter;

import android.graphics.drawable.Icon;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.choejun_yeong.blocker_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.turnout_score_item_name)
    public TextView textView;

    @BindView(R.id.turnout_score_item_progress)
    public NumberProgressBar progressBar;



    public ScoreListViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
