package com.example.vlad.openaq.retrofit;

import android.support.annotation.NonNull;

import com.example.vlad.openaq.network.NetworkChecker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public final class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private final RxJava2CallAdapterFactory original;
    private final NetworkChecker networkChecker;

    private RxErrorHandlingCallAdapterFactory(NetworkChecker networkChecker) {
        this.original = RxJava2CallAdapterFactory.create();
        this.networkChecker = networkChecker;
    }

    public static CallAdapter.Factory create(NetworkChecker networkChecker) {
        return new RxErrorHandlingCallAdapterFactory(networkChecker);
    }

    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType,
                                 @NonNull Annotation[] annotations,
                                 @NonNull Retrofit retrofit) {
        return new RxCallAdapterWrapper(original.get(returnType, annotations, retrofit), networkChecker);
    }
}
