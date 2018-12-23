package com.bakudynamics.android.bootstrap.views;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.bakudynamics.android.bootstrap.models.Model;
import com.bakudynamics.android.bootstrap.vendor.JsonParser;

import java.io.Serializable;

public interface Extractor {

    default <T extends Model> T getModel(Class<? extends Model> className) {
        Intent intent = getIntent();
        String modelJson = intent.getStringExtra(className.getName());
        return (T) JsonParser.parse(modelJson, className);
    }

    default int getInteger(String tag, int def) {
        if (getIntent() == null)
            return def;
        if (getIntent().hasExtra(tag))
            return getIntent().getIntExtra(tag, def);

        return def;
    }


    default long getLong(String tag, long def) {
        if (getIntent() == null)
            return def;
        if (getIntent().hasExtra(tag))
            return getIntent().getLongExtra(tag, def);

        return def;
    }

    default float getFloat(String tag, float def) {
        if (getIntent() == null)
            return def;
        if (getIntent().hasExtra(tag))
            return getIntent().getFloatExtra(tag, def);

        return def;
    }

    default double getDouble(String tag, double def) {
        if (getIntent() == null)
            return def;
        if (getIntent().hasExtra(tag))
            return getIntent().getDoubleExtra(tag, def);

        return def;
    }

    @Nullable
    default String getString(String tag) {
        if (getIntent() == null)
            return null;

        if (getIntent().hasExtra(tag))
            return getIntent().getStringExtra(tag);

        return null;
    }


    default boolean getBoolean(String tag, boolean def) {
        if (getIntent() == null)
            return def;
        if (getIntent().hasExtra(tag))
            return getIntent().getBooleanExtra(tag, def);

        return def;
    }


    default CharSequence getCharSequence(String tag) {
        if (getIntent() == null)
            return null;

        if (getIntent().hasExtra(tag))
            return getIntent().getCharSequenceExtra(tag);

        return null;
    }


    default <T extends Serializable> T getModel(String className) {
        Intent intent = getIntent();
        return (T) intent.getSerializableExtra(className);
    }

    Intent getIntent();
}
