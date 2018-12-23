package com.bakudynamics.android.bootstrap.views.binders;

import android.app.Activity;
import android.support.annotation.AnyRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface ActivityViewBinder extends ViewBinder {

    default void bindText(@IdRes int textViewId, @Nullable String text) {
        if (getContext() == null)
            return;
        bindText(getContext().findViewById(textViewId), text);
    }


    default void bindImage(@IdRes int imageResource, @AnyRes int contentResource) {
        if (getContext() == null)
            return;
        bindImage(getContext().findViewById(imageResource), contentResource);
    }

    default void bindImage(@IdRes int imageResource, @NonNull String contentResource) {
        if (getContext() == null)
            return;
        bindImage(getContext().findViewById(imageResource), contentResource);
    }


    @Override
    Activity getContext();
}
