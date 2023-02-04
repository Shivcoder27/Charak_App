package com.example.hackthisfall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class searchPatient extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   webView=(WebView) findViewById(R.id.webView);
        setContentView(R.layout.activity_search_patient);
        WebView myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        WebSettings webSettings = myWebView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webView.getSettings().setJavaScriptEnabled(true); // true/false to enable disable JavaScript support
        myWebView.loadUrl("https://ssas.netlify.app/");

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack() ) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}