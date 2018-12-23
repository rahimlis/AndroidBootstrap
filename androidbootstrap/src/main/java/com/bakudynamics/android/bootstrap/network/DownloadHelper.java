package com.bakudynamics.android.bootstrap.network;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.bakudynamics.android.bootstrap.R;
import com.bakudynamics.android.bootstrap.utils.DialogUtils;
import com.bakudynamics.android.bootstrap.utils.Logger;
import com.bakudynamics.android.bootstrap.utils.PermissionChecker;
import com.bakudynamics.android.bootstrap.utils.Utils;
import com.bakudynamics.android.bootstrap.vendor.FileManager;
import com.bakudynamics.android.bootstrap.views.BaseActivity;

import java.util.HashMap;

public class DownloadHelper {

    private CompositeDownloadListener listener;
    private BaseActivity context;
    private static final String TAG = "DownloadHelper";
    private DownloadManager downloadManager;
    private HashMap<Long, Downloadable> downloadsMap = new HashMap<>();
    private Downloadable cache;

    private String notificationTitle;
    private String destinationFolderName;
    private String notificationDescription;

    public DownloadHelper(BaseActivity context) {
        this.context = context;

        listener = new CompositeDownloadListener();

        if (context != null) {
            downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            notificationTitle = context.getString(R.string.app_name);
            destinationFolderName = context.getString(R.string.app_name);
        }
    }

    public boolean addDownload(Downloadable downloadable) {
        return addDownload(downloadable, null);
    }


    public boolean addDownload(Downloadable downloadable, DownloadManager.Request request) {
        if (context == null) {
            log("Context is null. Cancelling download");
            return false;
        }

        if (downloadable == null) return onDownloadableNull();

        if (PermissionChecker.hasWriteExternalPermission(context))
            return tryInitDownload(downloadable, request);

        return requestWritePermission(downloadable);
    }

    private boolean requestWritePermission(Downloadable downloadable) {
        log("requesting permission from user..");
        cache = downloadable;
        PermissionChecker.requestWritePermission(context);
        return false;
    }

    private boolean tryInitDownload(Downloadable downloadable, DownloadManager.Request request) {
        if (Utils.isDownloadManagerEnabled(context)) {
            init(downloadable, request);
            return true;
        } else {
            DialogUtils.showEnableDownloadManager(context);
            return false;
        }
    }

    private boolean onDownloadableNull() {
        log("downloadable is null");

        if (listener != null)
            listener.onFail("Downloadable is null");

        return false;
    }


    private void init(Downloadable downloadable, DownloadManager.Request request) {

        if (downloadManager == null)
            return;

        if (request == null)
            request = buildRequest(downloadable);

        long downloadId = downloadManager.enqueue(request);

        downloadsMap.put(downloadId, downloadable);

        log("Download of " + downloadable.getTitle() + " has been started. downloadId: " + downloadId);

        if (listener != null)
            listener.onStart();

        watchDownload(downloadId);
    }

    private void watchDownload(long downloadId) {

        new Thread() {
            boolean downloading = true;

            @Override
            public void run() {
                do {
                    final Cursor c;
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(downloadId);
                    c = downloadManager.query(query);
                    if (c.moveToFirst()) {
                        DownloadState state = DownloadState.retrieve(c);
                        new Handler(Looper.getMainLooper()).post(() -> {
                            downloading = handleState(state, downloadId);
                        });
                    }
                    c.close();
                } while (downloading);

            }
        }.start();
    }

    private boolean handleState(DownloadState state, long downloadId) {
        switch (state.getStatus()) {

            case DownloadManager.STATUS_SUCCESSFUL:
                onDownloadComplete(downloadId);
                return false;

            case DownloadManager.STATUS_FAILED:
                if (listener != null && context != null)
                    listener.onFail(DownloadErrorHandler.handle(state.getReason(), context));
                return false;

            case DownloadManager.STATUS_PAUSED:
                if (listener != null && context != null)
                    listener.onPaused(DownloadErrorHandler.handle(state.getReason(), context));
                return false;

            case DownloadManager.STATUS_RUNNING:
                if (listener != null && context != null)
                    listener.onProgress(state.getDownloadProgress());
                return true;
        }
        return true;
    }

    private void onDownloadComplete(long downloadId) {
        if (downloadsMap == null || !downloadsMap.containsKey(downloadId))
            return;

        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downloadId);
        Cursor c = downloadManager.query(query);
        if (c.moveToFirst()) {
            String uriString = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
            log("Successfully downloaded: " + uriString);
            if (listener != null) {
                listener.onSuccess(uriString, downloadsMap.get(downloadId));
                downloadsMap.remove(downloadId);
            }
            c.close();
        }
        cache = null;
    }

    @NonNull
    private DownloadManager.Request buildRequest(@NonNull Downloadable downloadResponse) {
        Uri uri = Uri.parse(downloadResponse.getUrl());
        log("downloadUri:" + uri.toString());
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(getNotificationTitle());
        request.setDescription(getNotificationDescription());
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setDestinationUri(FileManager.buildDestinationUri(downloadResponse, getDestinationFolderName()));
        return request;
    }


    private void log(String message) {
        Logger.e(TAG, message);
    }


    public void registerListener(DownloadListener uiListener) {
        if (listener != null)
            listener.registerListener(uiListener);
    }

    public void retry(DownloadManager.Request request) {
        if (cache != null)
            addDownload(cache, request);
    }

    public void retry() {
        if (cache != null)
            addDownload(cache, null);
    }


    public String getDestinationFolderName() {
        return destinationFolderName;
    }

    public void setDestinationFolderName(String destinationFolderName) {
        this.destinationFolderName = destinationFolderName;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }


}
