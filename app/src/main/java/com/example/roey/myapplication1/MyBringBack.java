package com.example.roey.myapplication1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

/**
 * Android Application Development Tutorial - 63 - Using a Constructor to pass Context
 */
public class MyBringBack extends View{

    Bitmap gball;
    float changingY =0;
    Typeface font;

    public MyBringBack(Context context) {
        super(context);

        gball = BitmapFactory.decodeResource(getResources(),R.drawable.greenball);
        changingY = 0;
        font = Typeface.createFromAsset(context.getAssets(),"gunit.TTF");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //set Background
        canvas.drawColor(Color.WHITE);

        //Text using (gunit.ttf file)
        Paint textPaint = new Paint();
        textPaint.setARGB(50,254,10,50);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);
        textPaint.setTypeface(font);
        canvas.drawText("mybringback",canvas.getWidth()/2,200,textPaint);

        //Ball animation
        canvas.drawBitmap(gball, (canvas.getWidth()) / 2, changingY, null);

        if (changingY < canvas.getHeight()){
            changingY += 10;

        }else {
            changingY = 0;
        }

        //blue Rectangle
        Rect middleRect = new Rect();
        middleRect.set(0, 400, canvas.getWidth(), 470);
        Paint ourBlue = new Paint();
        ourBlue.setColor(Color.BLUE);
        canvas.drawRect(middleRect, ourBlue);


        invalidate();

    }



}
