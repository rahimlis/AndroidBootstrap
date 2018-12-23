package com.bakudynamics.android.bootstrap.network;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class AsyncRequest extends AsyncTask<String, Void, Response> {
    private OkHttpClient client = new OkHttpClient();
    private Request.Builder builder = new Request.Builder();

    private AbstractListener listener;

    public AsyncRequest() {
    }

    public AsyncRequest addHeader(String header, String value) {
        if (builder != null)
            builder.addHeader(header, value);
        return this;
    }

    public AsyncRequest setUserAgent(String userAgent) {
        if (builder != null)
            builder.addHeader("User-Agent", userAgent);
        return this;
    }

    public AsyncRequest method(String method, RequestBody requestBody) {
        if (builder != null)
            builder.method(method, requestBody);
        return this;
    }

    public void doGet(String url, AsyncRequestListener listener) {
        doGet(url, null, listener);
    }


    public void doGet(String url, RequestBody requestBody, AsyncRequestListener listener) {
        this.listener = listener;
        method("GET", requestBody);
        execute(url);
    }

    public void doPost(String url, RequestBody requestBody, AsyncRequestListener listener) {
        this.listener = listener;
        method("POST", requestBody);
        execute(url);
    }

    public void doPost(String url, AsyncRequestListener listener) {
        doPost(url, null, listener);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (listener != null)
            listener.onStart();
    }


    @Override
    protected Response doInBackground(String... urls) {
        try {
            return client.newCall(getRequest(urls[0])).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        if (listener == null)
            return;

        listener.onFinish();

        if (response != null) {
            try {
                if (listener instanceof AsyncRequestListener)
                    ((AsyncRequestListener) listener).onSuccess(response.code(), response.headers(), response.body().bytes());
                else if (listener instanceof AsyncRequestRawListener)
                    ((AsyncRequestRawListener) listener).onSuccess(response);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            listener.onNullResponse();
    }

    @Nullable
    private Request getRequest(String url) {
        if (builder == null)
            return null;

        builder.url(url);

        return builder.build();
    }

    public Request.Builder getBuilder() {
        return builder;
    }

    public interface AsyncRequestListener extends AbstractListener {
        void onSuccess(int statusCode, Headers headers, byte[] responseBody);
    }

    public interface AsyncRequestRawListener extends AbstractListener {
        void onSuccess(Response response);
    }

    private interface AbstractListener {
        void onStart();

        void onNullResponse();

        void onFinish();
    }

}