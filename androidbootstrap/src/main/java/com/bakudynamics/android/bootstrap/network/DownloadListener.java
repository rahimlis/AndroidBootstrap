package com.bakudynamics.android.bootstrap.network;

public interface DownloadListener {
    void onStart();

    void onFail(String message);

    <T extends Downloadable> void  onSuccess(String uriString, T downloadResponse);

    void onProgress(long downloadProgress);

    void onPaused(String reason);
}