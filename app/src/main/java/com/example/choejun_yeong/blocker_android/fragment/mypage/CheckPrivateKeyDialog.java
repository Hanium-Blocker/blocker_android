package com.example.choejun_yeong.blocker_android.fragment.mypage;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.choejun_yeong.blocker_android.R;


public class CheckPrivateKeyDialog extends Dialog{
    private Context mContext;
    private TextView copyButton;
    private TextView privateKey_text;

    public CheckPrivateKeyDialog(@NonNull Context context, @NonNull String privateKey) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.
        setContentView(R.layout.check_privatekey_dialog);     //다이얼로그에서 사용할 레이아웃입니다.

        mContext = context;
        copyButton = findViewById(R.id.copy_button);
        privateKey_text = findViewById(R.id.private_key);

        privateKey_text.setText("개인키 : " + privateKey);

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyContact(privateKey);
                Toast.makeText(mContext, "복사되었습니다.", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

    public void copyContact(String privateKey) {
        // 클립보드 객체 얻기
        ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        // 클립데이터 생성
        ClipData clipData = ClipData.newPlainText("private",privateKey);
        // 클립보드에 추가
        clipboardManager.setPrimaryClip(clipData);
    }

}
