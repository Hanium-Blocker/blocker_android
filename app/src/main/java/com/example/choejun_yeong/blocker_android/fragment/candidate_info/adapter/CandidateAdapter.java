package com.example.choejun_yeong.blocker_android.fragment.candidate_info.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.choejun_yeong.blocker_android.DataModel.Candidate;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.activity.CandidateWebViewActivity;

import java.util.List;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.CandidateViewHolder>{

    private List<Candidate> candidateInfoList;
    private Context context;

    public CandidateAdapter(List<Candidate> candidateInfoList, Context context){
        this.candidateInfoList = candidateInfoList;
        this.context = context;
    }


    public class CandidateViewHolder extends RecyclerView.ViewHolder {
        public ImageView candidate_iv;
        public TextView candidate_num;
        public TextView candidate_name;
        public TextView candidate_party;
        public Button moreview_btn;

        public CandidateViewHolder(@NonNull View itemView) {
            super(itemView);

            candidate_iv = itemView.findViewById(R.id.candidate_iv);
            candidate_num = itemView.findViewById(R.id.candidate_num);
            candidate_name = itemView.findViewById(R.id.candidate_name);
            candidate_party = itemView.findViewById(R.id.candidate_party);
            moreview_btn = itemView.findViewById(R.id.moreview_btn);

        }
    }

    @NonNull
    @Override
    public CandidateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_candidate_item, viewGroup, false);
        CandidateViewHolder holder = new CandidateViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CandidateViewHolder holder, int i) {
        Candidate candidateInfo = candidateInfoList.get(i);
        holder.candidate_num.setText(String.valueOf(candidateInfo.getNumber())+"번");
        holder.candidate_name.setText(candidateInfo.getName());
        holder.candidate_party.setText(candidateInfo.getParty());
        holder.moreview_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CandidateWebViewActivity.class);
                intent.putExtra("webUrl",candidateInfo.getCampaign_link());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return candidateInfoList.size();
    }
}
