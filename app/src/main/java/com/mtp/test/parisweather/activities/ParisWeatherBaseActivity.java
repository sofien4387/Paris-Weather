package com.mtp.test.parisweather.activities;

import static com.mtp.test.parisweather.utils.ParisWeatherConstants.CONNECTIVITY_CHANGE_ACTION;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mtp.test.parisweather.R;
import com.mtp.test.parisweather.broadcasts.ParisWeatherBroadCast;

public class ParisWeatherBaseActivity extends AppCompatActivity{

    protected ParisWeatherBroadCast mChangeConnectionReceiver;

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mChangeConnectionReceiver = new ParisWeatherBroadCast();

        IntentFilter filter = new IntentFilter(CONNECTIVITY_CHANGE_ACTION);

        registerReceiver(mChangeConnectionReceiver, filter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        initToolBarViewConnectionChecker();

    }

    private void initToolBarViewConnectionChecker() {

        View errorBarView = findViewById(R.id.toolbar_sub_error_bar);
        mChangeConnectionReceiver.setConnectionToolBar(errorBarView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mChangeConnectionReceiver != null) {
            this.unregisterReceiver(mChangeConnectionReceiver);
        }
    }

}