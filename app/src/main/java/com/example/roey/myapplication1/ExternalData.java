package com.example.roey.myapplication1;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by roey on 03/02/2016.
 * if yoh have an app\game and you update it , the data that saved with sharedperfs\internal will be deleted - we need to save it external.
 * Android Application Development Tutorial - 104 - External Storage State
 * Android Application Development Tutorial - 105 - Spinners and ArrayAdapter
 * Android Application Development Tutorial - 106 - OnItemSelected and File Directories
 * Android Application Development Tutorial - 107 - Toggling Visibility
 * Android Application Development Tutorial - 108 - InputStream and OutputStream
 * Android Application Development Tutorial - 109 - Write External Data Permission
 * Android Application Development Tutorial - 110 - Media Scanner Connection
 *
 */
public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private TextView canWrite , canRead ;
    private String state;
    boolean canW , canR ;
    Spinner spinner;
    String[] paths =  {"Music" , "Pictures" , "Download"} ;
    File path = null ;
    File file = null ;
    EditText saveFile;
    Button confirm,save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);

        canWrite = (TextView) findViewById(R.id.tvCanWrite);
        canRead = (TextView) findViewById(R.id.tvCanRead);

        confirm = (Button) findViewById(R.id.bConfirmSaveAs);
        save = (Button) findViewById(R.id.bSaveFile);
        saveFile = (EditText) findViewById(R.id.etSaveAs);

        confirm.setOnClickListener(this);
        save.setOnClickListener(this);

        checkState();



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this , android.R.layout.simple_spinner_item , paths) ;

        spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);



            }

    private void checkState() {
        state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // read and write
            canWrite.setText("true");
            canRead.setText("true");
            canW = canR = true ;
        } else if (state.equals((Environment.MEDIA_MOUNTED_READ_ONLY))) {
            // read but cant write
            canWrite.setText("false");
            canRead.setText("true");
            canW = false;
            canR = true ;
        } else
            canWrite.setText("false");
        canRead.setText("false");
        canW = canR = false;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View arg0, int position, long id) {

        position = spinner.getSelectedItemPosition();
        switch (position){
            case 0:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;

            case 1:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;

            case 2:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;



        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()){
            case R.id.bSaveFile:
                String f = saveFile.getText().toString();
                file = new File(path , f + ".mp3");

                checkState();

                if (canW == canR == true ) {

          //          path = mkdirs();

                    try {
                        InputStream is = getResources().openRawResource(R.raw.tada);
                        OutputStream os = new FileOutputStream(file);
                        byte[] data = new byte[is.available()];
                        is.read(data);
                        os.write(data);
                        is.close();
                        os.close();

                        Toast t = Toast.makeText(ExternalData.this , "File has been saved" , Toast.LENGTH_LONG) ;
                        t.show();

                        // update files for the user to use
                        MediaScannerConnection.scanFile(ExternalData.this ,
                                new String[] {file.toString()} ,
                                null ,
                                new MediaScannerConnection.OnScanCompletedListener(){


                                    @Override
                                    public void onScanCompleted(String path, Uri uri) {

                                    Toast t = Toast.makeText(ExternalData.this , "Scan Complete" , Toast.LENGTH_LONG) ;
                                    t.show();
                                    }
                                });


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


                break;


            case R.id.bConfirmSaveAs:

                save.setVisibility(View.VISIBLE);
                break;




        }
    }


}

