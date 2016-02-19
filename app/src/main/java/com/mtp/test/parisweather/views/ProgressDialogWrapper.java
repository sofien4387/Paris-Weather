package com.mtp.test.parisweather.views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import com.mtp.test.parisweather.application.ParisWeatherApplication;

/**
 * This class of progress dialog wrapper used for avoid to
 * instantiate the dialog with multiple unused line.
 *
 * @author Rahmouni Sofien
 */
public class ProgressDialogWrapper {

    private ProgressDialog mProgressDialog;

    public ProgressDialogWrapper(Context context, String title, String message) {

        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIndeterminate(true);
    }

    public void showDialog() {
        mProgressDialog.show();
    }

    public void dismissDialog() {
        mProgressDialog.dismiss();
    }
}
