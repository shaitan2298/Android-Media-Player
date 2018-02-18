package com.example.android.classicmusic;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by ankita on 11/8/2017.
 */

public class Profile extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.profile);
    }
}
