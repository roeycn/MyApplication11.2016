package com.example.roey.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Android Application Development Tutorial - 67 - Setting up a SurfaceView class
 */
public class GFXsurface extends Activity implements View.OnTouchListener {

    MyBringBackSurface ourSurfaceView ;
    float x, y , sX , sY ,fX , fY ;
    float dX , dY , aniX , aniY , scaledX , scaledY ;
    Bitmap test , plus ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ourSurfaceView = new MyBringBackSurface(this);
        ourSurfaceView.setOnTouchListener(this);

        x=0;
        y=0;
        sX = 0;
        sY = 0;
        fX = 0;
        fY = 0;
        dX = dY = aniX = aniY = scaledX = scaledY = 0;

        test = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
        plus = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_greenplus);


        setContentView(ourSurfaceView);


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
    public boolean onTouch(View v, MotionEvent event) {

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
                break;
            case MotionEvent.ACTION_UP:
                fX = event.getX();
                fY = event.getY();

                dX =fX-sX;
                dY =fY-sY;
                scaledX = dX/60;
                scaledY = dY/60;


                // we will set x & y to 0 again for that the ball wont be painted again
                x=y=0;
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
    public class MyBringBackSurface extends SurfaceView implements Runnable {

        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = false;


        public MyBringBackSurface(Context context) {
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
                    canvas.drawBitmap(test,x-(test.getWidth()/2) , y-(test.getHeight()/2) , null);
                }

// adding the + option
                if(sX!=0 && sY!=0){
                    canvas.drawBitmap(plus,sX-(plus.getWidth()/2) , sY-(plus.getHeight()/2) , null);
                }

                if(fX!=0 && fY!=0){
                    // animation of the ball
                    canvas.drawBitmap(test,fX-(test.getWidth()/2)-aniX , fY-(test.getHeight()/2)-aniY , null);

                    canvas.drawBitmap(plus,fX-(plus.getWidth()/2) , fY-(plus.getHeight()/2) , null);
                }

                aniX = aniX + scaledX;
                aniY = aniY + scaledY;


                ourHolder.unlockCanvasAndPost(canvas);

            }

        }



    }


}
