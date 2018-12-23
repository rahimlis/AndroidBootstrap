package com.bakudynamics.android.bootstrap.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import com.bakudynamics.android.bootstrap.R;


public class DialogUtils {

    public static void showEnableDownloadManager(Context context, String title, String message, String positiveButtonMessage) {

        if (context == null)
            return;

        Logger.e("DialogUtils", "Download manager is not enabled. Showing user a dialogue to enable it");

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonMessage, (dialog, which) -> {
                    if (dialog != null)
                        dialog.dismiss();

                    Utils.startSettingsActivity(context);
                });
        builder.show();
    }

    public static void showEnableDownloadManager(Context context, @StringRes int title,
                                                 @StringRes int message, @StringRes int positiveButtonMessage) {
        if (context == null)
            return;
        String msg = context.getString(message);
        String ttl = context.getString(message);
        String pBm = context.getString(message);
        showEnableDownloadManager(context, msg, ttl, pBm);
    }

    public static void showEnableDownloadManager(Context context) {
        showEnableDownloadManager(context, R.string.bootstrap_title_download_manager,
                R.string.bootstrap_message_download_manager_enable,
                R.string.bootstrap_message_go_to_enable);
    }


}
