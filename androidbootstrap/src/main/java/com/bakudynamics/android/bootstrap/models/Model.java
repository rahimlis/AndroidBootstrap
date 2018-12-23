package com.bakudynamics.android.bootstrap.models;

import android.support.annotation.Nullable;

import com.google.gson.Gson;

/**
 * Created by rahim on 13/07/17.
 */

public class Model {

    @Nullable
    public String toJson() {
        try {
            Gson gson = new Gson();
            return gson.toJson(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
