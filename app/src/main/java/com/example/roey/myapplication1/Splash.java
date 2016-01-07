package com.example.roey.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.prefs.Preferences;


public class Splash extends Activity {



    MediaPlayer ourSong ;
    MediaPlayer ourSong2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ourSong = MediaPlayer.create(Splash.this , R.raw.app_intro_sound);
        ourSong2 = MediaPlayer.create(Splash.this , R.raw.scary);

        //Android Application Development Tutorial - 57 - Accessing Preferences from an Activity
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean musicOnOff = getPrefs.getBoolean("checkbox", true);

        String values = getPrefs.getString("musicselect", "1");


        if (musicOnOff == true) {
            if (values.contentEquals("1")) {
                ourSong.start();
            } else if (values.contentEquals("2")) {
                ourSong2.start();
            }
        }





        Thread timer = new Thread() {
        public void run(){
            try {
                sleep(3000);

            } catch (InterruptedException e){
                e.printStackTrace();

            }finally {
                Intent openMainActivity = new Intent("com.example.roey.myapplication1.Menu");
                startActivity(openMainActivity);
            }
        }
        };
        timer.start();



    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
