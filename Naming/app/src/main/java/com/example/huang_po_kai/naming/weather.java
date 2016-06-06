package com.example.huang_po_kai.naming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class weather extends AppCompatActivity {
    WebView wv2;
    ProgressBar pb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        wv2 = (WebView) findViewById(R.id.wv2);
        pb2 = (ProgressBar) findViewById(R.id.pb2);
        wv2.getSettings().setJavaScriptEnabled(true); //啟動JavaScript
        //wv2.getSettings().setBuiltInZoomControls(true); //啟動縮放功能
        wv2.getSettings().setUseWideViewPort(true); // WebView 自適螢幕大小

        //wv2.invokeZoomPicker(); //顯示縮放工具
        wv2.setWebViewClient(new WebViewClient()); //建立WebViewClient物件並設定給Webview
        wv2.setScrollbarFadingEnabled(true); //滾動條去條白邊
        wv2.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//滾動條去條白邊


        wv2.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                pb2.setProgress(progress); //設定進度
                pb2.setVisibility(progress < 100 ? View.VISIBLE :
                        View.GONE); // 依進度來讓進度條顯示或消失
            }
        });
        wv2.loadUrl("http://living.pccu.edu.tw/other-home/yms-weather-3.asp");
        //wv.loadUrl("http://m.pccu.edu.tw/kurogo/calendar/");
    }
}
