package com.example.choejun_yeong.blocker_android.fragment.admin_election.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.choejun_yeong.blocker_android.R;

public class ElectionManageViewHolder extends RecyclerView.ViewHolder {
    public TextView election_name;
    public ImageButton modify,delete;


    public ElectionManageViewHolder(@NonNull View itemView) {
        super(itemView);

        election_name = itemView.findViewById(R.id.manage_election_name);
        modify = itemView.findViewById(R.id.manage_election_modify_btn);
        delete = itemView.findViewById(R.id.manage_election_delete_btn);

    }

}
