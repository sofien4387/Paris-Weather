package com.mtp.test.parisweather.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.mtp.test.parisweather.R;

/**
 * This class of dialog wrapper used for avoid to instantiate the dialog with multiple unused line.
 *
 * @author Rahmouni Sofien
 */
public class DialogWrapper implements DialogInterface.OnClickListener {

    private AlertDialog.Builder alertDialogBuilder;

    public DialogWrapper(Context context, String title, String message) {

        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        String ok = context.getString(R.string.global_positive);
        alertDialogBuilder.setPositiveButton(ok, this);
    }

    public void showDialog() {
        alertDialogBuilder.show();
    }

    public void dismissDialog() {
        alertDialogBuilder.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        // ignore
    }
}
