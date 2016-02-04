package com.example.roey.myapplication1;

import com.plattysoft.leonids.modifiers.AlphaModifier;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.ScaleModifier;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;

public class Leonids extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leonids);


        findViewById(R.id.button1).setOnClickListener(this);

        findViewById(R.id.button2).setOnClickListener(this);



    }

    @Override
    public void onClick(View arg0) {

        switch(arg0.getId()) {
            case R.id.button1:

                // Dust

                new ParticleSystem(this, 4, R.drawable.dust, 3000)
                        .setSpeedByComponentsRange(-0.025f, 0.025f, -0.06f, -0.08f)
                        .setAcceleration(0.00001f, 30)
                        .setInitialRotationRange(0, 360)
                        .addModifier(new AlphaModifier(255, 0, 1000, 3000))
                        .addModifier(new ScaleModifier(0.5f, 2f, 0, 1000))
                        .oneShot(findViewById(R.id.emiter_bottom), 4);


                // FIREWORKS

                ParticleSystem ps = new ParticleSystem(this, 100, R.drawable.star_pink, 800);
                ps.setScaleRange(0.7f, 1.3f);
                ps.setSpeedRange(0.1f, 0.25f);
                ps.setRotationSpeedRange(90, 180);
                ps.setFadeOut(200, new AccelerateInterpolator());
                ps.oneShot(arg0, 70);

                ParticleSystem ps2 = new ParticleSystem(this, 100, R.drawable.star_white, 800);
                ps2.setScaleRange(0.7f, 1.3f);
                ps2.setSpeedRange(0.1f, 0.25f);
                ps.setRotationSpeedRange(90, 180);
                ps2.setFadeOut(200, new AccelerateInterpolator());
                ps2.oneShot(arg0, 70);


// confeti left & right

                new ParticleSystem(this, 80, R.drawable.confeti2, 10000)
                        .setSpeedModuleAndAngleRange(0f, 0.1f, 180, 180)
                        .setRotationSpeed(144)
                        .setAcceleration(0.000017f, 90)
                        .emit(findViewById(R.id.emiter_top_right), 8);

                new ParticleSystem(this, 80, R.drawable.confeti3, 10000)
                        .setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
                        .setRotationSpeed(144)
                        .setAcceleration(0.000017f, 90)
                        .emit(findViewById(R.id.emiter_top_left), 8);


            break;


            case R.id.button2:

                // Fireworks dont stop
          //      new ParticleSystem(this, 50, R.drawable.star_pink, 1000, R.id.background_hook)
          //              .setSpeedRange(0.1f, 0.25f)
          //              .emit(arg0, 100);


                ParticleSystem ps3 = new ParticleSystem(this, 100, R.drawable.star_pink, 1000);
                //size of the star
                ps3.setScaleRange(0.2f, 0.2f);
                ps3.setSpeedModuleAndAngleRange(0.07f, 0.16f, 0, 180);
                ps3.setRotationSpeedRange(90, 180);
                ps3.setAcceleration(0.00013f, 90);
                ps3.setFadeOut(200, new AccelerateInterpolator());
                ps3.emit(arg0, 100);


//Emiter With Gravity Example

                new ParticleSystem(this, 100, R.drawable.star_pink, 3000)
                        .setAcceleration(0.00013f, 90)
                        .setSpeedByComponentsRange(0f, 0f, 0.05f, 0.1f)
                        .setFadeOut(200, new AccelerateInterpolator())
                        .emitWithGravity(arg0, Gravity.BOTTOM, 30);

/*
                ParticleSystem ps = new ParticleSystem(this, 100, R.drawable.star_pink, 800);
                ps.setScaleRange(0.7f, 1.3f);
                ps.setSpeedRange(0.1f, 0.25f);
                ps.setAcceleration(0.0001f, 90);
                ps.setRotationSpeedRange(90, 180);
                ps.setFadeOut(200, new AccelerateInterpolator());
                ps.oneShot(arg0, 100);
*/
                break;

        }


    }


    // Stars follow the finger touch
    private ParticleSystem ps;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Create a particle system and start emiting
                ps = new ParticleSystem(this, 100, R.drawable.star_pink, 800);
                ps.setScaleRange(0.7f, 1.3f);
                ps.setSpeedRange(0.05f, 0.1f);
                ps.setRotationSpeedRange(90, 180);
                ps.setFadeOut(200, new AccelerateInterpolator());
                ps.emit((int) event.getX(), (int) event.getY(), 40);
                break;
            case MotionEvent.ACTION_MOVE:
                ps.updateEmitPoint((int) event.getX(), (int) event.getY());
                break;
            case MotionEvent.ACTION_UP:
                ps.stopEmitting();
                break;
        }
        return true;
    }
}