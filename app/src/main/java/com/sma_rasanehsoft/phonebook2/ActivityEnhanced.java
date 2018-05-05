package com.sma_rasanehsoft.phonebook2;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by alavi on 11/14/2017.
 */

public class ActivityEnhanced extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        App.ACTIVITY=this;
    }
}
