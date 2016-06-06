package com.example.huang_po_kai.naming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class calendarActivity extends AppCompatActivity {
    WebView wv;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        wv = (WebView) findViewById(R.id.wv);
        pb = (ProgressBar) findViewById(R.id.pb);
        wv.getSettings().setJavaScriptEnabled(true); //啟動JavaScript
        wv.getSettings().setBuiltInZoomControls(true); //啟動縮放功能
        //wv.getSettings().setUseWideViewPort(true); // WebView 自適螢幕大小
        wv.invokeZoomPicker(); //顯示縮放工具
        wv.setWebViewClient(new WebViewClient()); //建立WebViewClient物件並設定給Webview

        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view,int progress){
                pb.setProgress(progress); //設定進度
                pb.setVisibility(progress <100? View.VISIBLE:
                        View.GONE); // 依進度來讓進度條顯示或消失
            }
        });
        wv.loadUrl("https://calendar.google.com/calendar/embed?mode=AGENDA&src=pccu.edu.tw_blcke48f8jv7rd96hs8oeb06ro@group.calendar.google.com&ctz=Asia/Taipei");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(wv.canGoBack()){ //如果WebView有上一頁
            wv.goBack();    //回上一頁
            return;
        }
        super.onBackPressed(); //呼叫
    }
}
