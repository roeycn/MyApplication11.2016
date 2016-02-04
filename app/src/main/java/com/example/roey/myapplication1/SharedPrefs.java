package com.example.roey.myapplication1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by roey on 01/02/2016.
 * Android Application Development Tutorial - 95 - Saving Data with SharedPreferences
 * Android Application Development Tutorial - 96 - SharedPreferences Editor
 * Android Application Development Tutorial - 97 - Loading SharedPreferences Data
 */
public class SharedPrefs extends Activity implements View.OnClickListener {

    EditText sharedData;
    TextView dataResults;
    public static String filename = "MySharedString" ;
    SharedPreferences someData ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedprefs);

        setupVariables();
        someData = getSharedPreferences(filename , 0);
        


    }

    private void setupVariables() {
        Button save = (Button)findViewById(R.id.bSave);
        Button load = (Button)findViewById(R.id.bLoad);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);

        save.setOnClickListener(this);
        load.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.bSave:
                String stringData = sharedData.getText().toString();
                SharedPreferences.Editor editor = someData.edit();
                editor.putString("sharedString" , stringData);
                editor.commit();
                break;

            case  R.id.bLoad:
                someData = getSharedPreferences(filename , 0);
                String dataReturned = someData.getString("sharedString" , "Couldnt Load Data");
                dataResults.setText(dataReturned);
                break;


        }

    }
}
