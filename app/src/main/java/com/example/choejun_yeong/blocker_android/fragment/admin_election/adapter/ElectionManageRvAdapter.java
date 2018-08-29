package com.example.choejun_yeong.blocker_android.fragment.admin_election.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.choejun_yeong.blocker_android.DataModel.Candidate;
import com.example.choejun_yeong.blocker_android.DataModel.Election;
import com.example.choejun_yeong.blocker_android.R;

import java.util.List;


public class ElectionManageRvAdapter extends RecyclerView.Adapter<ElectionManageViewHolder>{
    private List<Election> modellist;
    private Context context;

    public ElectionManageRvAdapter(List model, Context context) {
        modellist = model;
        this.context = context;
    }


    @NonNull
    @Override
    public ElectionManageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_admin_election_item, viewGroup, false);
        ElectionManageViewHolder holder = new ElectionManageViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ElectionManageViewHolder holder, int i) {
        holder.election_name.setText(modellist.get(i).getElection_name());

        holder.modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("@@@@","modify");
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("@@@@","delete");
            }
        });
    }


    @Override
    public int getItemCount() {
        return modellist.size();
    }
}
