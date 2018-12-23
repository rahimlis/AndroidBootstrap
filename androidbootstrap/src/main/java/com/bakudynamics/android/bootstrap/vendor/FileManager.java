package com.bakudynamics.android.bootstrap.vendor;

import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.bakudynamics.android.bootstrap.network.Downloadable;
import com.bakudynamics.android.bootstrap.utils.Logger;

import java.io.File;


public class FileManager {


    public static boolean delete(File file) {
        Logger.e("FileManager", "deleting file on path: " + file.getPath());
        return file.delete();
    }


    public static boolean delete(String path) {
        File file = new File(path);
        Logger.e("FileManager", "deleting file on path: " + file.getPath());
        return file.delete();
    }

    public static boolean exists(String path) {
        File file = new File(path);
        Logger.e("FileManager", "file on path exists: " + file.exists());
        return file.exists();
    }

    public static boolean isSdCardPresent() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    public static Uri buildDestinationUri(@NonNull String folder, @NonNull String fName, @NonNull String extension) {

        String filename = fName + "." + extension;

        String filepath = isSdCardPresent() ? dirOnPath(folder) : Environment.getExternalStorageDirectory().getAbsolutePath();

        makeDirsOnPath(filepath);

        File f = new File(filepath + "/" + filename);

        return Uri.fromFile(f);
    }

    @NonNull
    private static String dirOnPath(@NonNull String folder) {
        return Environment.getExternalStoragePublicDirectory(folder) + "";
    }

    public static void makeDirsOnPath(String filepath) {
        new File(filepath).mkdirs();
    }

    public static Uri buildDestinationUri(@NonNull Downloadable downloadable, @NonNull String destinationFolder) {
        Boolean isSDPresent = FileManager.isSdCardPresent();

        String filename = downloadable.getTitle() + downloadable.getExtension();

        String filepath = isSDPresent ?
                dirOnPath(destinationFolder)
                : Environment.getExternalStorageDirectory().getAbsolutePath();

        makeDirsOnPath(filepath);

        File f = new File(filepath + "/" + filename);
        downloadable.setFilePath(f.getAbsolutePath());
        return Uri.fromFile(f);
    }


}
