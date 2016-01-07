package com.example.roey.myapplication1;

import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.RectEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by roey on 04/01/2016.
 */
public class GameTest2 extends Activity {

TextView tv2;
TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gametest2);


    tv2 = (TextView) findViewById(R.id.tv2);
    tv3 = (TextView) findViewById(R.id.tv3);

        ObjectAnimator anim = ObjectAnimator.ofObject(tv2,"backgroundColor", new ArgbEvaluator(), Color.RED, Color.BLUE);
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


    }


}
