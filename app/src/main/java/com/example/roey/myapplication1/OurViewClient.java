package com.example.roey.myapplication1;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Android Application Development Tutorial - 91 - Set WebView Client for a Brower app
 */
public class OurViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView v, String url) {
        v.loadUrl(url);
        return true ;
    }
}
