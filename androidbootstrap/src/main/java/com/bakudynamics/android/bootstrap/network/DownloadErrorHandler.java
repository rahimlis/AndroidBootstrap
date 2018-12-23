package com.bakudynamics.android.bootstrap.network;

import android.app.DownloadManager;
import android.content.Context;

import com.bakudynamics.android.bootstrap.R;
import com.bakudynamics.android.bootstrap.utils.Logger;

public class DownloadErrorHandler {

    public static String handle(int errorCode, Context context) {
        if (context == null) {
            Logger.e("DownloadErrorHandler", "Context is null");
            return null;
        }

        String message;

        switch (errorCode) {
            case DownloadManager.ERROR_CANNOT_RESUME:
                message = context.getString(R.string.bootstrap_error_cannot_resume);
                break;
            case DownloadManager.ERROR_DEVICE_NOT_FOUND:
                message = context.getString(R.string.bootstrap_error_cannot_find_sd_card);
                break;
            case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
                message = context.getString(R.string.bootstrap_error_already_downloaded);
                break;
            case DownloadManager.ERROR_FILE_ERROR:
                message = context.getString(R.string.bootstrap_error_file);
                break;
            case DownloadManager.ERROR_HTTP_DATA_ERROR:
                message = context.getString(R.string.bootstrap_error_http);
                break;
            case DownloadManager.ERROR_INSUFFICIENT_SPACE:
                message = context.getString(R.string.bootstrap_error_insufficient_space);
                break;
            case DownloadManager.ERROR_TOO_MANY_REDIRECTS:
                message = context.getString(R.string.bootstrap_error_too_many_redirects);
                break;
            case DownloadManager.ERROR_UNHANDLED_HTTP_CODE:
                message = context.getString(R.string.bootstrap_error_unhandled_http);
                break;
            case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                message = context.getString(R.string.bootstrap_message_queued_for_wifi);
                break;
            case DownloadManager.PAUSED_UNKNOWN:
                message = context.getString(R.string.bootstrap_message_paused_uknown);
                break;
            case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
                message = context.getString(R.string.bootstrap_message_waiting_for_network);
                break;
            case DownloadManager.PAUSED_WAITING_TO_RETRY:
                message = context.getString(R.string.bootstrap_message_waiting_for_retry);
                break;

            default:
                message = context.getString(R.string.bootstrap_error_unknown);
                break;
        }

        Logger.e("DownloadErrorHandler", message);

        return message;
    }

}
