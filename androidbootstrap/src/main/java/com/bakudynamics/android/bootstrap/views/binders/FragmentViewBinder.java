package com.bakudynamics.android.bootstrap.views.binders;

import android.support.annotation.AnyRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public interface FragmentViewBinder extends ViewBinder {

    default void bindText(@IdRes int textViewId, @Nullable String text) {
        if (getContainerView() == null)
            return;
        bindText(getContainerView().findViewById(textViewId), text);
    }


    default void bindImage(@IdRes int imageResource, @AnyRes int contentResource) {
        if (getContainerView() == null)
            return;
        bindImage(getContainerView().findViewById(imageResource), contentResource);
    }

    default void bindImage(@IdRes int imageResource, @NonNull String contentResource) {
        if (getContainerView() == null)
            return;
        bindImage(getContainerView().findViewById(imageResource), contentResource);
    }

    View getContainerView();
}
