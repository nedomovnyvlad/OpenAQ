package com.example.vlad.openaq.di.module;

import android.support.annotation.NonNull;

import com.example.vlad.openaq.api.CityService;
import com.example.vlad.openaq.network.NetworkChecker;
import com.example.vlad.openaq.retrofit.RxErrorHandlingCallAdapterFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.example.vlad.openaq.Constants.Api.BASE_URL;

@Module
public class RetrofitModule {

    @Provides
    @NonNull
    @Singleton
    public Retrofit provideRetrofit(NetworkChecker networkChecker, OkHttpClient okHttpClient, ObjectMapper objectMapper) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create(networkChecker))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public CityService provideCityService(Retrofit retrofit) {
        return retrofit.create(CityService.class);
    }
}
