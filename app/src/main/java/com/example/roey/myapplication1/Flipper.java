package com.example.roey.myapplication1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by roey on 31/01/2016. - Android Application Development Tutorial - 94 - ViewFlipper Example
 */
public class Flipper extends Activity implements View.OnClickListener {

    ViewFlipper flippy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);

        flippy = (ViewFlipper) findViewById(R.id.viewFlipper);
        flippy.setOnClickListener(this);

        // set auto flipping every 0.5 second
        flippy.setFlipInterval(500);
        flippy.startFlipping();


    }

    @Override
    public void onClick(View v) {

        flippy.showNext();
    }
}
