package com.mtp.test.parisweather.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.mtp.test.parisweather.listener.ParisWeatherConnectionListener;
import com.mtp.test.parisweather.utils.ConnectionUtils;

import static com.mtp.test.parisweather.utils.ParisWeatherConstants.CONNECTIVITY_CHANGE_ACTION;


/**
 * Broadcast receiver used to detect any implicit action from the device :
 *
 * E.g:
 * - connection change.
 *
 * @author Rahmouni Sofien
 */
public class ParisWeatherBroadCast extends BroadcastReceiver {

    private View view;
    private ParisWeatherConnectionListener connectionListener;

    /**
     * Setter of connection listener.
     *
     * @param connectionListener instance of connection listener.
     */
    public void setConnectionListener(ParisWeatherConnectionListener connectionListener) {
        this.connectionListener = connectionListener;
    }

    /**
     * Setter of connection listener tool bar indicator.
     * @param view
     */
    public void setConnectionToolBar(View view) {
        this.view = view;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (CONNECTIVITY_CHANGE_ACTION.equals(action)) {
            // Check internet connection
            if (connectionListener != null) {
                connectionListener.onConnectionChanged(intent);
            }

            // Set visibility connection toolbar
            if (view != null) {
                // Set visibility connection toolbar
                if (ConnectionUtils.isConnected(context)) {
                    view.setVisibility(View.GONE);
                } else {
                    view.setVisibility(View.VISIBLE);

                }
            }

        }
    }
}
