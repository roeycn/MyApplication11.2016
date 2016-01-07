package com.example.roey.myapplication1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import static android.graphics.Color.*;

public class Activity2 extends AppCompatActivity {

    int tmp ;
    int pmin ;
    int index ;
    int progress ;
    private TextView Activity2text ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String values = getData.getString("list" , "4");
        if( values.contentEquals("1")){
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
        }else if( values.contentEquals("2")){
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        }else if( values.contentEquals("3")){
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        }else if( values.contentEquals("4")) {
            getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        }
        Activity2text = (TextView) findViewById(R.id.activity2textview);
           }

    /**
    public Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public  boolean onPreferenceChange(Preference preference, Object value) {
            SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            String values = getData.getString("list", "4");
            if (values.contentEquals("1")) {
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            } else if (values.contentEquals("2")) {
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            } else if (values.contentEquals("3")) {
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            } else if (values.contentEquals("4")) {
                getWindow().getDecorView().setBackgroundColor(Color.BLACK);
            }
        return true;
        }
    };
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cool_menu, menu);
        return true;




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.Aboutus:
                Intent i =  new Intent("com.example.roey.myapplication1.ABOUT") ;
                startActivity(i);
                break;

            case  R.id.preferences:
                Intent p = new Intent("com.example.roey.myapplication1.PREFS") ;
                startActivity(p);
                break;

            case  R.id.exit:
                finish();
                break;
        }
                return false ;
    }
}
