package com.example.vlad.openaq.di.module;

import android.support.annotation.NonNull;

import com.example.vlad.openaq.network.ChangeableBaseUrl;
import com.example.vlad.openaq.network.ChangeableBaseUrlInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class NetworkModule {

    @Provides
    @NonNull
    @Singleton
    public OkHttpClient provideOkHttpClient(ChangeableBaseUrlInterceptor changeableBaseUrlInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(changeableBaseUrlInterceptor)
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public ChangeableBaseUrl provideChangeableBaseUrl() {
        return new ChangeableBaseUrl();
    }

    @Provides
    @NonNull
    @Singleton
    public ChangeableBaseUrlInterceptor provideChangeableBaseUrlInterceptor(ChangeableBaseUrl changeableBaseUrl) {
        return new ChangeableBaseUrlInterceptor(changeableBaseUrl);
    }
}
