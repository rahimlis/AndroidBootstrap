package com.bakudynamics.android.bootstrap.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

public class Utils {

    public static boolean isDownloadManagerEnabled(Context context) {
        if (context == null)
            return false;

        int state = context.getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");

        if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED ||
                state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {
            return false;
        }
        return true;
    }

    public static void startSettingsActivity(Context context) {
        if (context == null)
            return;
        try {
            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + "com.android.providers.downloads"));
            context.startActivity(intent);

        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
            context.startActivity(intent);
        }
    }
}
