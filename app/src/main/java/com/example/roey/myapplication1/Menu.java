package com.example.roey.myapplication1;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.ActionBarActivity;

/**
 * Android Application Development Tutorial - 52 - Making MenuItems do something
 *
 * skipping 56 57 58
 */
public class Menu extends ListActivity  {


    String classes[] = {"MainActivity" , "TextPlay" ,"Email" ,"Camera" ,
                          "Data" ,"GFX" , "GFXsurface" , "SoundStuff" , "GameTest" , "GameTest2" ,"Slider" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Android Application Development Tutorial - 61 - Full Screen Activities
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,WindowManager.LayoutParams.FLAG_FULLSCREEN );



        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));



    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String cheese = classes[position];


        Class ourClass = null;
        try {

        ourClass = Class.forName("com.example.roey.myapplication1." + cheese);

        Intent ourIntent = new Intent(Menu.this,ourClass) ;
        startActivity(ourIntent);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }



//  Android Application Development Tutorial - 51 - Setting up a Menu with MenuInflater
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
       // super.onCreateOptionsMenu(menu);
       // MenuInflater blowUp = getMenuInflater();
       // blowUp.inflate(R.menu.cool_menu , menu);
       // return true;

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;


    }


//  Android Application Development Tutorial - 52 - Making MenuItems do something
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.Aboutus:

                Intent i =  new Intent("com.example.roey.myapplication1.ABOUT") ;
                startActivity(i);


                break;

            case  R.id.preferences:

                break;
        }

            return false ;

    }
}

