package com.example.roey.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

/**
 *Android Application Development Tutorial - 62 - Custom Animation Class
 */
public class GFX extends Activity {

    MyBringBack ourView;
    PowerManager.WakeLock wL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //WakeLock to keep you app from sleeping
        PowerManager pM = (PowerManager)getSystemService(Context.POWER_SERVICE);
        wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK , "Whatever");


        super.onCreate(savedInstanceState);

        wL.acquire();

        ourView = new MyBringBack(this);
        setContentView(ourView);

    }

    @Override
    protected void onPause() {
        super.onPause();
        wL.release();
    }
}
