package com.example.vlad.openaq;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.vlad.openaq.di.AppComponent;
import com.example.vlad.openaq.di.module.NetworkModule;
import com.example.vlad.openaq.network.NetworkChecker;

public class TestApp extends App {

    @NonNull
    @Override
    protected AppComponent.Builder prepareAppComponent() {
        return super.prepareAppComponent()
                .networkModule(new NetworkModule() {
                    @NonNull
                    @Override
                    public NetworkChecker provideNetworkChecker(Context context) {
                        return new NetworkChecker(context) {
                            @Override
                            public boolean isOnline() {
                                // Internet connection is not needed because of web server mock
                                return true;
                            }
                        };
                    }
                });
    }
}
