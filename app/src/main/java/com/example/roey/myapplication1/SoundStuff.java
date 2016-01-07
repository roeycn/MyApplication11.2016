package com.example.roey.myapplication1;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

/**
 *Android Application Development Tutorial - 79 - SoundPool helps with explosions
 */
public class SoundStuff extends Activity implements View.OnClickListener, View.OnLongClickListener {


    SoundPool sp ;
    int explosion = 0;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);

        sp = new SoundPool(5 , AudioManager.STREAM_MUSIC , 0);
        explosion = sp.load(this , R.raw.shotgun , 1);
        mp = MediaPlayer.create(this, R.raw.scary);


    }


    @Override
    public void onClick(View v) {
        if (explosion !=0)
        sp.play(explosion , 1 ,1 ,0 ,0 ,1);

    }


    // Long Click will do another sound.
    @Override
    public boolean onLongClick(View v) {
        mp.start();
        return false;
    }
}
