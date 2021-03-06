package com.example.choejun_yeong.blocker_android.fragment.admin_candidate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.example.choejun_yeong.blocker_android.DataModel.AuthResponse;
import com.example.choejun_yeong.blocker_android.DataModel.Candidate;
import com.example.choejun_yeong.blocker_android.DataModel.CandidateUpload;
import com.example.choejun_yeong.blocker_android.R;
import com.example.choejun_yeong.blocker_android.service.CandidateService;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;

import static android.app.Activity.RESULT_OK;

public class CandidateManageCreate extends DialogFragment {
    @NonNull
    private CompositeDisposable mCompositeDisposable;
    private EditText candidate_name;
    private EditText candidate_num;
    private EditText candidate_party;
    private EditText candidate_birth;
    private EditText candidate_gender;
    private EditText candidate_campaignlink;
//    private Button candidate_img_btn;
    private Fragment frag;
    private int position;
    private CandidateUpload candidate;
    MultipartBody.Part body;

    public static CandidateManageCreate newInstance(int position, CandidateManageFragment frag) {

        Bundle args = new Bundle();

        CandidateManageCreate fragment = new CandidateManageCreate();
        fragment.setArguments(args);
        fragment.frag= frag;
        fragment.position = position;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        candidate = new CandidateUpload();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_admin_candidate_create, null);

        candidate_name = view.findViewById(R.id.manage_candidate_create_name);
        candidate_num = view.findViewById(R.id.manage_candidate_create_num);
        candidate_party = view.findViewById(R.id.manage_candidate_create_party);
        candidate_birth = view.findViewById(R.id.manage_candidate_create_birth);
        candidate_gender = view.findViewById(R.id.manage_candidate_create_gender);
        candidate_campaignlink = view.findViewById(R.id.manage_candidate_create_campaignlink);
//        candidate_img_btn = view.findViewById(R.id.manage_candidate_create_image);

//        candidate_img_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), AlbumSelectActivity.class);
//                intent.putExtra(Constants.INTENT_EXTRA_LIMIT, 3);
//                startActivityForResult(intent, Constants.REQUEST_CODE);
//            }
//        });

        builder.setView(view)
                .setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        candidate.setName(candidate_name.getText().toString());
                        candidate.setNumber(Integer.valueOf(candidate_num.getText().toString()));
                        candidate.setParty(candidate_party.getText().toString());
                        candidate.setBirth(candidate_birth.getText().toString());
                        candidate.setGender(candidate_gender.getText().toString());
                        candidate.setCampaign_link(candidate_campaignlink.getText().toString());
                        mCompositeDisposable.add(CandidateService.getInstance().addCandidate(position, candidate)
                                .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableObserver<AuthResponse>() {
                                    @Override
                                    public void onNext(AuthResponse response) {
                                        Log.d("@@@","Test1");
                                        if(response.getCode()==200){
                                            Reloadfrag();
                                        }
                                        else if(response.getCode()==409){

                                            Toast.makeText(getContext(), "후보자 중복 오류", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.d("@@@","Test2");
                                        Toast.makeText(getContext(), "후보자 생성 오류", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onComplete() {
                                        Log.d("@@@","Test3");
                                        Toast.makeText(getContext(), "후보자 추가 성공", Toast.LENGTH_SHORT).show();
                                    }
                                }));

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("@@@@","@@@@@@@@"+candidate_name.getText().toString());
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        return builder.create();

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == Constants.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
//            ArrayList<Image> images = data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
//            StringBuffer stringBuffer = new StringBuffer();
//            for (int i = 0, l = images.size(); i < l; i++) {
////                stringBuffer.append(images.get(i).path + "\n");
//                File file = new File(images.get(i).path);
//                RequestBody reqfile = RequestBody.create(MediaType.parse("image/*"),file);
//                body = MultipartBody.Part.createFormData("image_file",file.getName(),reqfile);
//                candidate.setImage_file(file);
//
//                Log.d("@@@","file LOg : "+file);
//
//            }
//
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        bind();
    }

    @Override
    public void onPause() {
        super.onPause();
        unBind();
    }

    private void bind() {
        mCompositeDisposable = new CompositeDisposable();

    }

    private void unBind() {
        mCompositeDisposable.clear();
    }

    private void Reloadfrag() {
        getFragmentManager().beginTransaction()
                .detach(frag)
                .attach(frag)
                .commit();
    }

}
