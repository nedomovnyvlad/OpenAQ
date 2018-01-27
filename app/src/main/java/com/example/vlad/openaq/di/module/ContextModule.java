package com.example.vlad.openaq.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    @Provides
    @NonNull
    @Singleton
    public Context provideContext(Application application) {
        return application.getApplicationContext();
    }
}

