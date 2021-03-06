package com.example.choejun_yeong.blocker_android.fragment.admin_candidate.adapter;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.example.choejun_yeong.blocker_android.DataModel.AuthResponse;
import com.example.choejun_yeong.blocker_android.DataModel.Candidate;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.fragment.admin_candidate.CandidateManageFragment;
import com.example.choejun_yeong.blocker_android.fragment.admin_candidate.CandidateManageModify;
import com.example.choejun_yeong.blocker_android.service.CandidateService;

import java.io.Serializable;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CandidateManageAdapter extends RecyclerView.Adapter<CandidateManageAdapter.CandidateManageViewHolder>{
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    public static Candidate clicked_candidate;

    private List<Candidate> candidateInfoList;
    private CandidateManageFragment fragment;

    public CandidateManageAdapter(List model, CandidateManageFragment fragment) {
        candidateInfoList = model;
        this.fragment = fragment;
        mCompositeDisposable = new CompositeDisposable();
    }

    public class CandidateManageViewHolder extends RecyclerView.ViewHolder {
        public TextView candidate_num, candidate_name;
        public ImageButton modify,delete,imageButton;

        public CandidateManageViewHolder(@NonNull View itemView) {
            super(itemView);

            candidate_num = itemView.findViewById(R.id.manage_candidate_num);
            candidate_name = itemView.findViewById(R.id.manage_candidate_name);
            modify = itemView.findViewById(R.id.manage_candidate_modify_btn);
            delete = itemView.findViewById(R.id.manage_candidate_delete_btn);
            imageButton = itemView.findViewById(R.id.manage_candidate_image_btn);

        }
    }

    @NonNull
    @Override
    public CandidateManageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_admin_candidate_item, viewGroup, false);
        CandidateManageViewHolder holder = new CandidateManageViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CandidateManageViewHolder holder, int i) {
        holder.candidate_num.setText("기호"+ String.valueOf(candidateInfoList.get(i).getNumber()) + "번");
        holder.candidate_name.setText(candidateInfoList.get(i).getName());

        Log.d("@@@@idid",""+ candidateInfoList.get(i).getElection_id());
        Log.d("@@@@idid",""+ candidateInfoList.get(i).getNumber());
        Log.d("@@@@idid",""+ candidateInfoList.get(i).getName());

        holder.modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("@@@@","modify");
                CandidateManageModify dialfrag = CandidateManageModify.newInstance(candidateInfoList.get(i), fragment);
                dialfrag.show(fragment.getActivity().getSupportFragmentManager(),"candidate modify fragment");
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("@@@@","delete");
                mCompositeDisposable.add(CandidateService.getInstance().deleteCandidate(candidateInfoList.get(i).getElection_id(), candidateInfoList.get(i).getNumber())
                        .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<AuthResponse>() {
                            @Override
                            public void onNext(AuthResponse response) {
                                if(response.getCode()==200){
                                    Log.d("@@@","DeleteTest1");
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

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fragment.getContext(), AlbumSelectActivity.class);
                intent.putExtra(Constants.INTENT_EXTRA_LIMIT, 3);
                clicked_candidate = new Candidate();
                clicked_candidate.setElection_id(candidateInfoList.get(i).getElection_id());
                clicked_candidate.setNumber(candidateInfoList.get(i).getNumber());
                clicked_candidate.setName(candidateInfoList.get(i).getName());
//                intent.putExtra("electionid",candidateInfoList.get(i).getElection_id());
//                intent.putExtra("number",candidateInfoList.get(i).getNumber());
//                Log.d("@@@LOGLOGLOGLOG",""+intent.getExtras().get("number")+"//"+intent.getExtras().get("electionid")+intent.getExtras().get(Constants.INTENT_EXTRA_LIMIT));
//                Log.d("#####","@@@@"+candidateInfoList.get(i).getNumber()+",,,,"+candidateInfoList.get(i).getElection_id());
                fragment.startActivityForResult(intent, Constants.REQUEST_CODE);
            }
        });
    }



    @Override
    public int getItemCount() {
        return candidateInfoList.size();
    }

    private void Reloadfrag() {
        fragment.getFragmentManager().beginTransaction()
                .detach(fragment)
                .attach(fragment)
                .commit();
    }
}