package com.bakudynamics.android.bootstrap.factories;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.bakudynamics.android.bootstrap.models.Model;
import com.google.gson.Gson;

import java.io.Serializable;


public class IntentFactory extends Factory {


    public static Intent getIntent(Context context, Class<?> className) {
        return new Intent(context, className);
    }

    public static IntentBuilder builder() {
        return new IntentBuilder();
    }

    public static class IntentBuilder {
        private Intent intent;
        private Context from;

        public IntentBuilder() {
            this.intent = new Intent();
        }

        public IntentBuilder from(Context context) {
            this.from = context;
            return this;
        }

        public IntentBuilder to(Class<?> className) {
            this.intent.setClass(from, className);
            return this;
        }

        public IntentBuilder with(String tag, int data) {
            intent.putExtra(tag, data);
            return this;
        }

        public IntentBuilder with(String tag, String data) {
            intent.putExtra(tag, data);
            return this;
        }


        public IntentBuilder with(String tag, boolean data) {
            intent.putExtra(tag, data);
            return this;
        }

        public IntentBuilder with(String tag, float data) {
            intent.putExtra(tag, data);
            return this;
        }

        public IntentBuilder with(String tag, double data) {
            intent.putExtra(tag, data);
            return this;
        }

        public IntentBuilder with(String tag, short data) {
            intent.putExtra(tag, data);
            return this;
        }


        public IntentBuilder with(Model data) {
            intent.putExtra(data.getClass().getName(), data.toJson());
            return this;
        }

        public IntentBuilder with(Model[] data) {
            if (data.length == 0)
                return this;

            if (data.length == 1)
                return with(data[0]);

            Gson gson = new Gson();

            String jsonStr = gson.toJson(data);

            intent.putExtra(data.getClass().getName(), jsonStr);

            return this;
        }

        public IntentBuilder with(String tag, Model data) {
            intent.putExtra(tag, data.toJson());
            return this;
        }


        public IntentBuilder with(String tag, Serializable serializable) {
            intent.putExtra(tag, serializable);
            return this;
        }


        public IntentBuilder with(String tag, Parcelable parcelable) {
            intent.putExtra(tag, parcelable);
            return this;
        }

        public Intent create() {
            return this.intent;
        }
    }


}
