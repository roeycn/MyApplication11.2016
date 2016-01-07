package com.example.roey.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by roey on 13/12/2015.
 * Android Application Development Tutorial - 50 - setResult for the Start Activity For Result
 * https://www.youtube.com/watch?v=AEA1qJFpheY
 *
 * 1. at comment -> writing in the edit text moving the string to next activity ( using bundle ).
 *
 * 2. the user will click startFor button -> select answer using the radiogroup -> press the return
 * button will back to the last activity with the selected result
 *
 */
public class Data extends Activity implements View.OnClickListener {

    Button start , startFor ;
    EditText sendET ;
    TextView gotAnswer ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);

        initialize();



    }

    private void initialize() {
        start = (Button) findViewById(R.id.bSA);
        startFor = (Button) findViewById(R.id.bSAFR);
        sendET = (EditText) findViewById(R.id.etSend);
        gotAnswer = (TextView)findViewById(R.id.tvGot);
        start.setOnClickListener(this);
        startFor.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.bSA:
                String bread = sendET.getText().toString();
                Bundle basket = new Bundle();
                basket.putString("key" , bread);
                Intent a = new Intent(Data.this , OpenedClass.class);
                a.putExtras(basket);
                startActivity(a);
                break;


            case R.id.bSAFR:
                Intent i  = new Intent(Data.this , OpenedClass.class);
                startActivityForResult(i , 0);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Bundle basket = data.getExtras();
            String s = basket.getString("answer");
            gotAnswer.setText(s);

        }
    }
}
