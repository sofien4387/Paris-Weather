package com.mtp.test.parisweather.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * This class of connection utils used to centralize any method which have relation with network using.
 *
 * E.g:
 * - check network connection.
 *
 * @author Rahmouni Sofien
 */
public class ConnectionUtils {
    /*
    * isConnected - Check if there is a NetworkConnection
    * @return boolean true means connection exist.
    */
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
