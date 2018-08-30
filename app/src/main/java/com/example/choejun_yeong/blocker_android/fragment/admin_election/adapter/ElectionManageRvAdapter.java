package com.example.choejun_yeong.blocker_android.fragment.admin_election.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.choejun_yeong.blocker_android.DataModel.AuthResponse;
import com.example.choejun_yeong.blocker_android.DataModel.Candidate;
import com.example.choejun_yeong.blocker_android.DataModel.Election;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.fragment.admin_election.ElectionManageFragment;
import com.example.choejun_yeong.blocker_android.fragment.admin_election.ElectionManageModify;
import com.example.choejun_yeong.blocker_android.service.ElectionService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class ElectionManageRvAdapter extends RecyclerView.Adapter<ElectionManageViewHolder>{
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    private List<Election> modellist;
    private ElectionManageFragment fragment;

    public ElectionManageRvAdapter(List model, ElectionManageFragment fragment) {
        modellist = model;
        this.fragment = fragment;
        mCompositeDisposable = new CompositeDisposable();
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
                ElectionManageModify dialfrag = ElectionManageModify.newInstance(fragment,modellist.get(i));
                dialfrag.show(fragment.getActivity().getSupportFragmentManager(),"election create fragment");
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCompositeDisposable.add(ElectionService.getInstance().deleteElection(modellist.get(i).getElection_id())
                        .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<AuthResponse>() {
                            @Override
                            public void onNext(AuthResponse response) {
                                if(response.getCode()==200){
                                    Reloadfrag();
                                }
                                else{
                                    Toast.makeText(fragment.getContext(), "예기치 못한 오류 발생", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(fragment.getContext(), "서버 오류 재시도 해주세요", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(fragment.getContext(), "선거 삭제 성공", Toast.LENGTH_SHORT).show();
                            }
                        }));
            }
        });
    }


    @Override
    public int getItemCount() {
        return modellist.size();
    }

    private void Reloadfrag() {
        fragment.getFragmentManager().beginTransaction()
                .detach(fragment)
                .attach(fragment)
                .commit();
    }
}
