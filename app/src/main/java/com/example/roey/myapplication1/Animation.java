package com.example.roey.myapplication1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by roey on 04/01/2016.
 */
public class Animation extends Activity {

    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    Button newButton;
    Button newButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);

/**
 *

    tv2 = (TextView) findViewById(R.id.tv2);
    tv3 = (TextView) findViewById(R.id.tv3);
    tv4 = (TextView) findViewById(R.id.tv4);
    tv5 = (TextView) findViewById(R.id.tv5);
    tv6 = (TextView) findViewById(R.id.tv6);
    tv7 = (TextView) findViewById(R.id.tv7);
    newButton = (Button)findViewById(R.id.button);
    newButton2 = (Button)findViewById(R.id.button2);


        ObjectAnimator anim = ObjectAnimator.ofObject(tv2,"backgroundColor", new ArgbEvaluator(), Color.RED, Color.BLUE);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setDuration(5000);
        anim.start();


      //This code will execute the animation to fade out the button. Notice that the "alpha" is designated as a string type.
      // The ObjectAnimator relies on reflection and uses the button's getAlpha() and setAlpha() methods to perform the animation.

      //  ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(tv3, "alpha", 0.2f);
      //  fadeAnim.start();

        ObjectAnimator scaleAnim = ObjectAnimator.ofFloat(tv3, "scaleX", 1.0f, 2.0f);
        scaleAnim.setDuration(3000);
        scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnim.setRepeatMode(ValueAnimator.REVERSE);
        scaleAnim.start();

        //the rate of change of that animation - the trajectory of the movement.
        ObjectAnimator moveAnim = ObjectAnimator.ofFloat(tv4, "Y", 500);
        moveAnim.setDuration(7000);
        moveAnim.setInterpolator(new BounceInterpolator());
        moveAnim.setRepeatCount(ValueAnimator.INFINITE);
        moveAnim.start();

        //We can add an AnimatorListenerAdapter to manage events during the animation lifecycle
        // such as onAnimationStart or onAnimationEnd:
/*
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(tv5, "alpha", 0.2f);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(Animation.this, "End!", Toast.LENGTH_SHORT).show();
            }
        });
        anim.start();
*/





         // We can play multiple ObjectAnimator objects together concurrently with the AnimatorSet with:
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(tv5, "scaleX", 1.0f, 2.0f).setDuration(5000),
                ObjectAnimator.ofFloat(tv5, "scaleY", 1.0f, 2.0f).setDuration(5000),
                ObjectAnimator.ofObject(tv5, "backgroundColor", new ArgbEvaluator(),/*Red*/0xFFFF8080, /*Blue*/0xFF8080FF).setDuration(9000)

        );
        set.start();

        // Define first set of animations
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(tv6, "scaleX", 2.0f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(tv6, "scaleY", 2.0f);
        AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(anim1, anim2);
// Define second set of animations
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(tv7, "X", 300);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(tv7, "Y", 300);
        AnimatorSet set2 = new AnimatorSet();
        set2.playTogether(anim3, anim4);
// Play the animation sets one after another
        AnimatorSet set3 = new AnimatorSet();
        set3.playSequentially(set1, set2);
        set3.start();



        // Inflate animation from XML
        android.view.animation.Animation animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

// Setup listeners (optional)
        animFadeOut.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {

            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {

            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {

            }


        });
// start the animation
        newButton.startAnimation(animFadeOut);



        android.view.animation.Animation animxmltry = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        newButton2.startAnimation(animxmltry);



    }


}
