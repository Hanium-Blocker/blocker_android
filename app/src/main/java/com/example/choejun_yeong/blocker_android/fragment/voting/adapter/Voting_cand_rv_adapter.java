package com.example.choejun_yeong.blocker_android.fragment.voting.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.choejun_yeong.blocker_android.DataModel.CandidateVO;
import com.example.choejun_yeong.blocker_android.DataModel.Candidate_Voting;
import com.example.choejun_yeong.blocker_android.R;

import java.util.List;

public class Voting_cand_rv_adapter extends RecyclerView.Adapter<VotingCandViewHolder>{

    private List<CandidateVO> modellist;
    private Context context;
    private int lastCheckedPosition = -1;
    private Button button;

    public Voting_cand_rv_adapter(List model, Context context,Button button) {
        modellist = model;
        this.context = context;
        this.button = button;
    }


    @NonNull
    @Override
    public VotingCandViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_voting_candidate_item, viewGroup, false);
        VotingCandViewHolder holder = new VotingCandViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VotingCandViewHolder holder, int i) {
        final CandidateVO model = modellist.get(i);
        holder.cand_name.setText(model.getName());


        if (i == lastCheckedPosition){
            holder.radioButton.setChecked(true);
        }else{
            holder.radioButton.setChecked(false);
        }
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastCheckedPosition = i;
                button.setEnabled(true);
                notifyItemRangeChanged(0, modellist.size());

            }
        });

        holder.cand_name.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                lastCheckedPosition = i;
                button.setEnabled(true);
                notifyItemRangeChanged(0, modellist.size());

            }
        });
    }


    public CandidateVO getSelectedItem(){
        CandidateVO model = modellist.get(lastCheckedPosition);
        return model;
    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }
}
