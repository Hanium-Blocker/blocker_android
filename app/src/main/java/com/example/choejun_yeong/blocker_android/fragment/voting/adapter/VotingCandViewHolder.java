package com.example.choejun_yeong.blocker_android.fragment.voting.adapter;

import android.provider.MediaStore;
import android.service.autofill.TextValueSanitizer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.choejun_yeong.blocker_android.R;

public class VotingCandViewHolder extends RecyclerView.ViewHolder {
    public TextView cand_name;
    public RadioButton radioButton;


    public VotingCandViewHolder(@NonNull View itemView) {
        super(itemView);

        cand_name = itemView.findViewById(R.id.voting_cand_name);
        radioButton = itemView.findViewById(R.id.voting_cand_radio);

    }

}
