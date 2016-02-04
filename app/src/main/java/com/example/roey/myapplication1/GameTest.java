package com.example.roey.myapplication1;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.AlphaModifier;
import com.plattysoft.leonids.modifiers.ScaleModifier;

import java.util.Random;

/**
 * Android Application Development Tutorial - 67 - Setting up a SurfaceView class
 * shared on github
 */
public class GameTest extends Activity implements View.OnTouchListener {

    GameMotion ourSurfaceView ;
    float x, y , sX , sY ,fX , fY ;
    float dX , dY , aniX , aniY , scaledX , scaledY ;
    Bitmap ball, plus ;
    int score = 0;
    int attempts = 0;
    int hits = 0;
    MediaPlayer strikemp;
    MediaPlayer touchmp;
    MediaPlayer testtesttes222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.gametest);

        ourSurfaceView = new GameMotion(this);
        ourSurfaceView.setOnTouchListener(this);

        setContentView(ourSurfaceView);
 //   ourSurfaceView = (GameMotion)findViewById(R.id.surfaceView);
        x=0;
        y=0;
        sX = 0;
        sY = 0;
        fX = 0;
        fY = 0;
        dX = dY = aniX = aniY = scaledX = scaledY = 0;

        ball = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
        plus = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_greenplus);

        strikemp = MediaPlayer.create(this, R.raw.slap);
        touchmp = MediaPlayer.create(this, R.raw.tada);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurfaceView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourSurfaceView.resume();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

//Sleeping to Achieve desired FPS
        try {
            Thread.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        x=event.getX();
        y=event.getY();

// adding the + option
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:


                sX = event.getX();
                sY = event.getY();
//this will make to ball to possible animated again each new drag  & drop process
                dX = dY = aniX = aniY = scaledX = scaledY = fX = fY = 0;

                if(strikemp.isPlaying())
                {
                    strikemp.seekTo(0);
                }

                break;

            case MotionEvent.ACTION_UP:
                fX = event.getX();
                fY = event.getY();
                dX =fX-sX;
                dY =fY-sY;
                scaledX = dX/60;
                scaledY = dY/60;
                strikemp.start();

                // we will set x & y to 0 again for that the ball wont be painted again
                x=y=0;

                attempts += 1;

                break;
        }
        // if we will set it to false , then the ball will notbe followed our
        // finger( no dragging ), true is update real time.
        return true;
    }

    /**
     * Android Application Development Tutorial - 68 - Setting up Animation Thread
     * (we merged 2 classes into 1).
     */
    public class GameMotion extends SurfaceView implements Runnable {

        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = false;
        Random rnd = new Random();
        int changingX1 =0;
        int changingX2 =150;
        int blueRecHeight = 0;

        public GameMotion(Context context) {
            super(context);
            ourHolder = getHolder();
        }

        public void pause() {
            isRunning = false ;
            while (true){
                try {
                    ourThread.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null ;
        }

        public void resume() {
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }


        @Override
        public void run() {

            while (isRunning){
                if (!ourHolder.getSurface().isValid())
                    continue;

                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(02, 02, 150);

                if(x!=0 && y!=0){
                    canvas.drawBitmap(ball,x-(ball.getWidth()/2) , y-(ball.getHeight()/2) , null);
                }

                // adding the + option
                if(sX!=0 && sY!=0){
                    canvas.drawBitmap(plus,sX-(plus.getWidth()/2) , sY-(plus.getHeight()/2) , null);
                }

                if(fX!=0 && fY!=0){
                    // animation of the ball
                    canvas.drawBitmap(ball,fX-(ball.getWidth()/2)-aniX , fY-(ball.getHeight()/2)-aniY , null);

                    canvas.drawBitmap(plus,fX-(plus.getWidth()/2) , fY-(plus.getHeight()/2) , null);
                }

                aniX = aniX + scaledX;
                aniY = aniY + scaledY;

                blueRecHeight = (int) ((canvas.getHeight()) * 0.9);

                //blue Rectangle
                Rect middleRect = new Rect();
                middleRect.set(changingX1, blueRecHeight, changingX2, canvas.getHeight());
                Paint ourBlue = new Paint();
                ourBlue.setColor(Color.YELLOW);
                canvas.drawRect(middleRect, ourBlue);



                //moving Rectangle animation
                 if (changingX2 < canvas.getWidth()) {
                     changingX1 += 2;
                     changingX2 += 2;
                     // Update score

                 }else {
                     changingX1 = 0;
                     changingX2 = 150;
                 }




                float BallWidth = ball.getWidth();
                float BallHeight =(ball.getHeight());

                float ballcurrentx = fX - (ball.getWidth() / 2) - aniX;
                float ballcurrenty = fY - (ball.getHeight()/2)-aniY ;

                float ballcurrentx1 = ballcurrentx ;
                float ballcurrentx2 = ballcurrentx + BallWidth;
                float ballcurrenty1 = ballcurrenty + BallHeight;

                float rectangleWidthSize = 150 ;
                float ballWidthSize = ball.getWidth();
                float totalWidthSize = rectangleWidthSize+ballWidthSize;

                float maxMinusMin;
                float max;
                float min;

                if (changingX2 > ballcurrentx2 ) {
                    max = changingX2;
                    min = ballcurrentx1;
                }
                else {
                    max = ballcurrentx2;
                    min = changingX1;
                }

                //if the ball will touch the moving object , sound & color performed.
               //     if (ballcurrentx > changingX1 && ballcurrentx < changingX2 && ballcurrenty>blueRecHeight) {
                  //    if ( (ballcurrentx1 > changingX1 && ballcurrentx1 < changingX2 || ballcurrentx2 > changingX1 &&  ballcurrentx2 <  changingX2 ) &&  ballcurrenty1>blueRecHeight) {

                        if ((max-min < totalWidthSize)&& ballcurrenty1>blueRecHeight ){
                        touchmp.start();
                        ourBlue.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                        canvas.drawRect(middleRect, ourBlue);



                    }

                // Update score
                if (touchmp.isPlaying()) {
                    score += 1;
                }

                // WHEN THE BALL CROSS THE END OF THE SCREEN  REDRAW IT.
                if (ballcurrenty > canvas.getHeight()) {
                    canvas.drawBitmap(ball,sX-(ball.getWidth()/2) , sY-(ball.getHeight()/2), null);
                }

                // draw score
                Paint ourScore = new Paint();
                ourScore.setColor(Color.argb(255, 249, 129, 0));
                ourScore.setTextSize(40);
                canvas.drawText("Score: " + score + "   Attempts: " + attempts, 10, 50, ourScore);



                ourHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
