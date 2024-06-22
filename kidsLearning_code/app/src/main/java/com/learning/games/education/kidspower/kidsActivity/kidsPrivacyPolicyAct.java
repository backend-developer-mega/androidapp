package com.learning.games.education.kidspower.kidsActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

import com.learning.games.education.kidspower.R;

public class kidsPrivacyPolicyAct extends AppCompatActivity {
    Context context;
    WebView webView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_privacy_policy);
        getSupportActionBar().hide();
        this.context = this;
        WebView webView2 = (WebView) findViewById(R.id.webView);
        this.webView = webView2;
        webView2.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl("https://sites.google.com/view/kids-learning-privacy");
    }

}
