package com.example.vlad.openaq.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public final class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private final RxJava2CallAdapterFactory original;
    private final Context context;

    private RxErrorHandlingCallAdapterFactory(Context context) {
        this.original = RxJava2CallAdapterFactory.create();
        this.context = context;
    }

    public static CallAdapter.Factory create(Context context) {
        return new RxErrorHandlingCallAdapterFactory(context);
    }

    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType,
                                 @NonNull Annotation[] annotations,
                                 @NonNull Retrofit retrofit) {
        return new RxCallAdapterWrapper(original.get(returnType, annotations, retrofit), context);
    }
}
