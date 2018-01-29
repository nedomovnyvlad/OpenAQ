package com.example.vlad.openaq.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.vlad.openaq.network.ChangeableBaseUrl;
import com.example.vlad.openaq.network.ChangeableBaseUrlInterceptor;
import com.example.vlad.openaq.network.NetworkChecker;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

import static com.example.vlad.openaq.Constants.HttpClient.CONNECT_TIMEOUT_MS;
import static com.example.vlad.openaq.Constants.HttpClient.READ_TIMEOUT_MS;
import static com.example.vlad.openaq.Constants.HttpClient.WRITE_TIMEOUT_MS;

@Module
public class NetworkModule {

    @Provides
    @NonNull
    @Singleton
    public OkHttpClient provideOkHttpClient(ChangeableBaseUrlInterceptor changeableBaseUrlInterceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT_MS, TimeUnit.MILLISECONDS)
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

    @Provides
    @NonNull
    @Singleton
    public NetworkChecker provideNetworkChecker(Context context) {
        return new NetworkChecker(context);
    }
}
