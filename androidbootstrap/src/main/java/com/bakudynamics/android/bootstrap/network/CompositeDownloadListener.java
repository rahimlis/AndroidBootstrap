package com.bakudynamics.android.bootstrap.network;

import java.util.ArrayList;

final class CompositeDownloadListener implements DownloadListener {


    private ArrayList<DownloadListener> listeners = new ArrayList<>();

    public void registerListener(DownloadListener listener) {
        if (listener != null)
            listeners.add(listener);
    }

    @Override
    public void onStart() {
        for (DownloadListener downloadListener : listeners)
            if (downloadListener != null)
                downloadListener.onStart();
    }

    @Override
    public void onFail(String message) {
        for (DownloadListener downloadListener : listeners)
            if (downloadListener != null)
                downloadListener.onFail(message);
    }

    @Override
    public void onSuccess(String uriString, Downloadable downloadResponse) {
        for (DownloadListener downloadListener : listeners)
            if (downloadListener != null)
                downloadListener.onSuccess(uriString, downloadResponse);

    }

    @Override
    public void onProgress(long downloadProgress) {
        for (DownloadListener downloadListener : listeners)
            if (downloadListener != null)
                downloadListener.onProgress(downloadProgress);
    }

    @Override
    public void onPaused(String reason) {
        for (DownloadListener downloadListener : listeners)
            if (downloadListener != null)
                downloadListener.onPaused(reason);
    }
}