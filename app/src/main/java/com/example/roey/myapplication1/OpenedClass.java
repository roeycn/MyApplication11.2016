package com.example.roey.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.prefs.Preferences;

/**
 * Created by roey on 13/12/2015.
 */
public class OpenedClass extends Activity implements View.OnClickListener , RadioGroup.OnCheckedChangeListener {

    TextView question ,test ;
    Button returnData ;
    RadioGroup  selectionList ;
    String gotBread , setData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initialize();

      //  Android Application Development Tutorial - 58 - More with Preferences
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = getData.getString("name" , "roeycohen");
        String values = getData.getString("list" , "4");
        if( values.contentEquals("1")){
            question.setText(et);
        }



      //  Bundle gotBasket = getIntent().getExtras();
      //  gotBread = gotBasket.getString("key");
      //  question.setText(gotBread);


    }

    private void initialize() {
        question = (TextView) findViewById(R.id.tvQuestion);
        test = (TextView) findViewById(R.id.tvText);
        returnData = (Button) findViewById(R.id.bReturn);
        returnData.setOnClickListener(this);
        selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
        selectionList.setOnCheckedChangeListener(this);

    }


    @Override
    public void onClick(View arg0) {
        Intent person = new Intent();
        Bundle backpack = new Bundle();
        backpack.putString("answer" , setData);
        person.putExtras(backpack);
        setResult(RESULT_OK , person);
        finish();


    }

    @Override
    public void onCheckedChanged(RadioGroup arg0, int arg1) {

        switch(arg1){
            case R.id.rCrazy:
            setData = "Probably right!";
                break;

            case R.id.rSexy:
            setData = "Definitely right!";
                break;

            case R.id.rBoth:
            setData = "Spot on!";
                break;
        }
        test.setText(setData);



    }
}
