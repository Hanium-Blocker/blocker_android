package com.example.choejun_yeong.blocker_android.fragment.voting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.choejun_yeong.blocker_android.R;

public class VotingRootFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentvotingroot,container,false);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.voting_container, new VotingMainFragment());
        transaction.commit();
        return view;

    }
}
