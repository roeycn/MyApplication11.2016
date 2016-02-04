package com.example.roey.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * Android Application Development Tutorial - 89 - Create a Browser with WebView
 */
public class SimpleBrowser extends Activity implements View.OnClickListener{

    EditText url;
    WebView ourBrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);

        ourBrow = (WebView) findViewById(R.id.wvBrowser);
        // enable javascript
        ourBrow.getSettings().setJavaScriptEnabled(true);
        //enable completly option to zoom in\out
        ourBrow.getSettings().setLoadWithOverviewMode(true);
        //set normal view port
        ourBrow.getSettings().setUseWideViewPort(true);

        ourBrow.setWebViewClient(new OurViewClient());

        try {
        ourBrow.loadUrl("http://www.nba.com");
        }catch (Exception e){
            e.printStackTrace();
        }



        url = (EditText) findViewById(R.id.etUrl);

        Button go = (Button) findViewById(R.id.bGo);
        Button back = (Button) findViewById(R.id.bBack);
        Button refresh = (Button) findViewById(R.id.bRefresh);
        Button forward = (Button) findViewById(R.id.bForoward);
        Button clearHistory = (Button) findViewById(R.id.bClear);

        go.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        forward.setOnClickListener(this);
        clearHistory.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bGo:
                String theWebsite = url.getText().toString();
                ourBrow.loadUrl(theWebsite);

                // Hiding the Keyboard after using the edit text
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(url.getWindowToken() , 0);
                break;

            case R.id.bBack:
                if (ourBrow.canGoBack())
                    ourBrow.goBack();
                break;

            case R.id.bForoward:
                if (ourBrow.canGoForward())
                    ourBrow.goForward();
                break;

            case R.id.bRefresh:
                ourBrow.reload();
                break;



            case R.id.bClear:
                ourBrow.clearHistory();
                break;


        }

    }
}
