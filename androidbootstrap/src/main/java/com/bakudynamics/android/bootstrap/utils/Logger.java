package com.bakudynamics.android.bootstrap.utils;

import android.util.Log;

public class Logger {

    private static boolean enabled = false;

    public static void enable() {
        enabled = true;
    }

    public static void e(String context, String message) {
        if (enabled)
            Log.e(context, message);
    }
}
