package com.bakudynamics.android.bootstrap.network;

public interface Downloadable {
    String getUrl();

    String getTitle();

    void setFilePath(String absolutePath);

    String getExtension();

}
