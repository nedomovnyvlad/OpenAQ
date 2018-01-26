package com.example.vlad.openaq.di.module;

import android.support.annotation.NonNull;

import com.example.vlad.openaq.api.CityService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class RetrofitModule {
    private static final String BASE_URL = "https://api.openaq.org/";

    @Provides
    @NonNull
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public CityService provideCityService(Retrofit retrofit) {
        return retrofit.create(CityService.class);
    }
}
