package com.bakudynamics.android.bootstrap.views.binders;

import android.content.Context;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bakudynamics.android.bootstrap.factories.IntentFactory;
import com.bakudynamics.android.bootstrap.models.Model;
import com.squareup.picasso.Picasso;

public interface ViewBinder {

    default void bindActivityStarter(View view, final Class<?> activityName, final Model... models) {
        bindOnClickListener(view, view1 -> {
            if (getContext() == null)
                return;
            if (models == null || models.length == 0)
                getContext().startActivity(IntentFactory.getIntent(getContext(), activityName));
            else
                getContext().startActivity(IntentFactory
                        .builder()
                        .from(getContext())
                        .to(activityName)
                        .with(models)
                        .create()
                );
        });
    }

    default IntentFactory.IntentBuilder bindActivityStarter(View view, final Class<?> activityName) {
        IntentFactory.IntentBuilder intent = IntentFactory.builder()
                .from(getContext())
                .to(activityName);

        bindOnClickListener(view, view1 -> {
            if (getContext() == null)
                return;
            getContext().startActivity(intent.create());
        });

        return intent;
    }


    default void bindImage(ImageView imageView, @NonNull String url) {
        if (imageView == null)
            return;
        Picasso.get().load(url).into(imageView);
    }

    default void bindImage(ImageView imageView, @AnyRes int contentResource) {
        if (imageView == null || contentResource == 0)
            return;
        Picasso.get().load(contentResource).into(imageView);
    }

    Context getContext();

    default void bindOnClickListener(View view, View.OnClickListener listener) {
        if (view == null)
            return;

        view.setOnClickListener(listener);
    }

    default void bindText(TextView textView, @Nullable String text) {
        if (textView == null)
            return;

        textView.setText(text);
    }






}
