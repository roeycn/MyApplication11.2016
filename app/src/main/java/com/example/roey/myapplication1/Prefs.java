package com.example.roey.myapplication1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;


//Android Application Development Tutorial - 56 - Creating a PreferenceActivity + my add ons

public class Prefs extends PreferenceActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);



    }


}