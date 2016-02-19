package com.mtp.test.parisweather.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mtp.test.parisweather.R;
import com.mtp.test.parisweather.listener.ParisWeatherConnectionListener;

public class ParisWeatherActivity extends ParisWeatherBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paris_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void setConnectionListener(ParisWeatherConnectionListener con) {
        mChangeConnectionReceiver.setConnectionListener(con);
    }

}
