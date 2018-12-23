package com.bakudynamics.android.bootstrap.network;

import android.app.DownloadManager;
import android.database.Cursor;

public class DownloadState {
    private long bytes_downloaded;
    private long bytes_total;
    private long download_percentage;
    private int reason;
    private int status;

    public DownloadState(long bytes_downloaded, long bytes_total, long download_percentage, int reason, int status) {
        this.bytes_downloaded = bytes_downloaded;
        this.bytes_total = bytes_total;
        this.download_percentage = download_percentage;
        this.reason = reason;
        this.status = status;
    }

    public static DownloadState retrieve(Cursor c) {
        final int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));//Get download status
        final long bytes_downloaded = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
        final long bytes_total = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
        final long download_percentage = bytes_total == 0 ? 0 : (bytes_downloaded * 100l) / bytes_total;
        final int reason = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_REASON));
        return new DownloadState(bytes_downloaded, bytes_total, download_percentage, reason, status);
    }

    public long getDownloadProgress() {
        return download_percentage;
    }

    public int getReason() {
        return reason;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "DownloadState{" +
                "bytes_downloaded=" + bytes_downloaded +
                ", bytes_total=" + bytes_total +
                ", download_percentage=" + download_percentage +
                ", reason=" + reason +
                ", status=" + status +
                '}';
    }
}