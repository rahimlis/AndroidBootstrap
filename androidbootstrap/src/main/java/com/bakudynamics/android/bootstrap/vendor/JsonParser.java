package com.bakudynamics.android.bootstrap.vendor;

import android.support.annotation.Nullable;

import com.bakudynamics.android.bootstrap.models.Model;
import com.bakudynamics.android.bootstrap.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by rahim on 23/08/17.
 */

public class JsonParser {

    @Nullable
    public static <T extends Model> T parse(String json, Class<T> className) {
        if (json == null)
            return null;

        Gson gson = new Gson();
        return gson.fromJson(json, className);
    }

    public static <T extends Model> ArrayList<T> parseJsonArray(JSONArray jsonArray, Class<T> className) {
        if (jsonArray == null || jsonArray.length() <= 0) {
            Logger.e("JsonParser", "array is empty");
            return new ArrayList<>();
        }
        ArrayList<T> items = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                T item = parse(jsonArray.getJSONObject(i).toString(), className);
                items.add(item);
            }
            return items;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static <T extends Model> ArrayList<T> parseJsonArray(String jsonStr) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<T>>() {
        }.getType();
        return gson.fromJson(jsonStr, listType);
    }
}
