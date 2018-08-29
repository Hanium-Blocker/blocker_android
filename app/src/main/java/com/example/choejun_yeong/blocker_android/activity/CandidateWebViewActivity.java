package com.example.choejun_yeong.blocker_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.choejun_yeong.blocker_android.R;

public class CandidateWebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    private WebSettings mWebSettings;
    private String webUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_webview);

        Intent intent = getIntent();
        webUrl = intent.getExtras().getString("webUrl");

        mWebView = (WebView)findViewById(R.id.candidate_webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);

        mWebView.loadUrl(webUrl);


    }

}
