package com.example.choejun_yeong.blocker_android.fragment.turnout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.example.choejun_yeong.blocker_android.R;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

import static android.app.Activity.RESULT_OK;
import static com.darsh.multipleimageselect.helpers.Constants.REQUEST_CODE;

public class TurnoutMainFragment extends Fragment {
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    Button btn;
    TextView text;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_turnout_main, container, false);

        text = view.findViewById(R.id.test_text);
        btn = view.findViewById(R.id.test_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent,"Select Picture"), REQUEST_CODE);
                Intent intent = new Intent(getActivity(), AlbumSelectActivity.class);
                intent.putExtra(Constants.INTENT_EXTRA_LIMIT, 3);
                startActivityForResult(intent, Constants.REQUEST_CODE);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<Image> images = data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0, l = images.size(); i < l; i++) {
                stringBuffer.append(images.get(i).path + "\n");
//                File file = new File(images.get(i).path);
            }
            text.setText(stringBuffer.toString());

        }
    }
}