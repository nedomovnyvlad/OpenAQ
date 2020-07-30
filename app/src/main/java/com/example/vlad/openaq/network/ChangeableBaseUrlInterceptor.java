package com.example.vlad.openaq.network;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ChangeableBaseUrlInterceptor implements Interceptor {

    private final ChangeableBaseUrl changeableBaseUrl;

    public ChangeableBaseUrlInterceptor(ChangeableBaseUrl changeableBaseUrl) {
        this.changeableBaseUrl = changeableBaseUrl;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();

        if (changeableBaseUrl.getUrl() != null) {
            HttpUrl newUrl = HttpUrl.parse(changeableBaseUrl.getUrl());

            if (newUrl != null) {
                request = request.newBuilder()
                        .url(newUrl)
                        .build();
            }
        }
        return chain.proceed(request);
    }
}