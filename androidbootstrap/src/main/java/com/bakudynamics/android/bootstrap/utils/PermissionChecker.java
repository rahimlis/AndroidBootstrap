package com.bakudynamics.android.bootstrap.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.bakudynamics.android.bootstrap.views.BaseActivity;


public class PermissionChecker {

    public static final int REQUEST_PERMISSION = 1619;

    private static boolean checkPermission(BaseActivity context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionCheck = ContextCompat.checkSelfPermission(context, permission);

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                //showing dialog to select image
                return true;
            } else {
                requestPermission(context, permission);
                return false;
            }
        }
        return true;
    }

    private static void requestPermission(BaseActivity context, String permission) {
        int permissionCheck = ContextCompat.checkSelfPermission(context, permission);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED)
            return;
        ActivityCompat.requestPermissions(context,
                new String[]{permission,}, REQUEST_PERMISSION);
    }

    public static void requestWritePermission(BaseActivity context) {
        requestPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public static boolean hasPermission(BaseActivity context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean hasWriteExternalPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public static boolean hasReadExternalPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
    }


    public static boolean hasAccessFineLocationPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
    }


    public static boolean hasAccessCoarseLocationPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
    }


    public static boolean hasCameraPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.CAMERA);
    }


    public static boolean hasReadContactsPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.READ_CONTACTS);
    }

    public static boolean hasWriteContactsPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.WRITE_CONTACTS);
    }

    public static boolean hasGetAccountsPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.GET_ACCOUNTS);
    }

    public static boolean hasRecordAudioPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.RECORD_AUDIO);
    }

    public static boolean hasReadPhoneStatePermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.READ_PHONE_STATE);
    }



    public static boolean hasCallPhonePermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.CALL_PHONE);
    }


    public static boolean hasAddVoiceMailPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.ADD_VOICEMAIL);
    }

    public static boolean hasUseSipPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.USE_SIP);
    }


    public static boolean hasSendSmsPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.SEND_SMS);
    }

    public static boolean hasReceiveSmsPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.RECEIVE_SMS);
    }

    public static boolean hasReadSmsPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.READ_SMS);
    }

    public static boolean hasRecieveWapPushPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.RECEIVE_WAP_PUSH);
    }

    public static boolean hasRecieveMmsPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.RECEIVE_MMS);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean hasAnswerCallPhonesPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.ANSWER_PHONE_CALLS);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean hasReadPhoneNumbersPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.READ_PHONE_NUMBERS);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public static boolean hasBodySensorsPermission(BaseActivity context) {
        return hasPermission(context, Manifest.permission.BODY_SENSORS);
    }

    private boolean hasWriteExternalPermission(Context context) {
        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int res = context.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }
}
