package com.example.roey.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by roey on 22/11/2015.
 */
public class Camera extends Activity implements View.OnClickListener {

    Button b;
    ImageButton ib;
    ImageView iv;
    final static int cameraData = 0;
    Bitmap bmp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);

        initialize();

        InputStream is = getResources().openRawResource(R.raw.qa) ;
        bmp = BitmapFactory.decodeStream(is);

    }

    private void initialize() {



        iv = (ImageView) findViewById(R.id.ivReturnPic);
        ib = (ImageButton) findViewById(R.id.ibTakePic);
        b = (Button) findViewById(R.id.bSetWall);

        b.setOnClickListener(this);
        ib.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bSetWall:
                try {
               //     if (bmp != null ) me addon - fix crash when no iamge displayed
                    getApplicationContext().setWallpaper(bmp);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.ibTakePic:

                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,cameraData);
                break;


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            iv.setImageBitmap(bmp);
        }

    }
}








