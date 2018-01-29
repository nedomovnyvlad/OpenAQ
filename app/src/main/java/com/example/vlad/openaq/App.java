package com.example.vlad.openaq;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.vlad.openaq.di.AppComponent;
import com.example.vlad.openaq.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private AppComponent appComponent;

    @NonNull
    public static App get(@NonNull Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = prepareAppComponent().build();

        appComponent.inject(this);

        Timber.plant(new Timber.DebugTree());
    }

    @NonNull
    protected AppComponent.Builder prepareAppComponent() {
        return DaggerAppComponent
                .builder()
                .application(this);
    }

    @NonNull
    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
