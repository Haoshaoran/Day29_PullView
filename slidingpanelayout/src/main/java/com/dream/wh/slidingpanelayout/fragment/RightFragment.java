package com.dream.wh.slidingpanelayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dream.wh.slidingpanelayout.R;

/**
 * Created by Administrator on 16-6-2.
 */
public class RightFragment extends Fragment{

    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.content_layout,null);
        webView = (WebView) view.findViewById(R.id.webView);
        return view;
    }
    public void setWebView(String urlString){
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        WebViewClient client=new WebViewClient();
        webView.setWebViewClient(client);
        webView.loadUrl(urlString);
    }
}
