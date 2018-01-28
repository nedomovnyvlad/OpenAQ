package com.example.vlad.openaq.di.module;

import android.support.annotation.NonNull;

import com.example.vlad.openaq.di.scope.ActivityScope;
import com.example.vlad.openaq.ui.activity.main.MainActivity;
import com.example.vlad.openaq.ui.activity.main.MainActivityModule;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidSupportInjectionModule.class})
public abstract class ApplicationModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity mainActivityInjector();

    @Provides
    @NonNull
    @Singleton
    public static ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }
}
