package com.example.roey.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity  {

     private TextView Maintext ;
     private Button AddButton ;
     private Button SubtractButton ;
     private Button NextActivityButton ;
     private int counter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Maintext  = (TextView) findViewById(R.id.textView1) ;

        AddButton = (Button)findViewById(R.id.button1) ;
        SubtractButton = (Button)findViewById(R.id.button2) ;
        NextActivityButton = (Button) findViewById(R.id.button3);









        AddButton.setOnClickListener(new Button.OnClickListener() {

                    public void onClick(View v) {

                        Toast.makeText(getApplicationContext(), "Button has been clicked!!! =)",Toast.LENGTH_SHORT).show();
                        counter++ ;
                        Maintext.setText("Your total is  " +counter);
                    }
                }
        );

        SubtractButton.setOnClickListener(new Button.OnClickListener() {

                   public void onClick(View v) {

                       Toast.makeText(getApplicationContext(), "Button has been clicked!!! =)",Toast.LENGTH_SHORT).show();
                       counter-- ;
                       Maintext.setText("Your total is  " +counter);
                                         }
                                     }
        );


        NextActivityButton.setOnClickListener(new Button.OnClickListener() {

        public void onClick(View v) {
            Intent openActivity2 = new Intent(MainActivity.this ,  Activity2.class);
            startActivity(openActivity2);


        }
                                              }

        );

        }

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
