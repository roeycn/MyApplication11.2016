package com.example.roey.myapplication1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by roey on 01/02/2016.
 * Android Application Development Tutorial - 98 - File Output Stream for Internal Storage
 * Android Application Development Tutorial - 99 - Writing Data to File Output Stream
 * Android Application Development Tutorial - 100 - File Input Stream
 *
 * Android Application Development Tutorial - 101 - Async Task class to load stuff
 * Android Application Development Tutorial - 102 - The 4 AsyncTask Methods
 *
 * Android Application Development Tutorial - 103 - ProgressDialog and Correction
 *
 */
public class InternalData extends Activity implements View.OnClickListener {


     EditText sharedData;
     TextView dataResults;

    FileOutputStream fos;
    String FILENAME = "InternalString" ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedprefs);

        setupVariables();



    }

    private void setupVariables() {
        Button save = (Button)findViewById(R.id.bSave);
        Button load = (Button)findViewById(R.id.bLoad);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
        save.setOnClickListener(this);
        load.setOnClickListener(this);

        try {
            fos = openFileOutput(FILENAME , Context.MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.bSave:

                String data = sharedData.getText().toString();
                // saving data via File
                /*
                File f = new File(FILENAME);     create file from a string
                try{
                    fos = new FileOutputStream(f);
            // write some data fos.close;
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            */
                try {
                fos = openFileOutput(FILENAME , Context.MODE_PRIVATE);
                fos.write(data.getBytes());
                fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;




            case  R.id.bLoad:
            new loadSomeStuff().execute(FILENAME);
                break;
        }
    }

    public  class loadSomeStuff extends AsyncTask<String, Integer, String>   {

        ProgressDialog dialog ;

        protected void onPreExecute(){
            //example of setting up something
            dialog = new ProgressDialog(InternalData.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(100);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String collected = null ;
            FileInputStream fis = null ;

            for (int i = 0 ; i<20 ; i++){
                publishProgress(5);
                try {
                    Thread.sleep(88);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            dialog.dismiss();
            try {
                fis = openFileInput(FILENAME);
                byte[] dataArray = new byte[fis.available()];

                while (fis.read(dataArray)!= -1){
                    collected = new String(dataArray);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    fis.close();
                    return collected;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        protected void onProgressUpdate(Integer... progress){
            dialog.incrementProgressBy(progress[0]);

        }


        protected void onPostExecute(String result){
            dataResults.setText(result);
        }


    }
}


