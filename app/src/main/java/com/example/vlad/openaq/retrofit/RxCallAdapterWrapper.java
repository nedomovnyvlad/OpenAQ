package com.example.vlad.openaq.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.vlad.openaq.util.NetworkUtils;

import java.lang.reflect.Type;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;

public class RxCallAdapterWrapper<R> implements CallAdapter<R, Object> {
    private final CallAdapter<R, Object> wrapped;
    private final Context context;

    public RxCallAdapterWrapper(CallAdapter<R, Object> wrapped, Context context) {
        this.wrapped = wrapped;
        this.context = context;
    }

    @Override
    public Type responseType() {
        return wrapped.responseType();
    }

    @Override
    public Object adapt(@NonNull Call<R> call) {
        Object adaptedByWrapped = wrapped.adapt(call);
        if (adaptedByWrapped instanceof Single) {
            return ((Single<Response<R>>) adaptedByWrapped)
                    .doOnSubscribe(ignored -> {
                        if (!NetworkUtils.isOnline(context)) {
                            throw new NoConnectivityException();
                        }
                    });
        } else {
            throw new IllegalStateException("Unable to adapt " + adaptedByWrapped.getClass().toString());
        }
    }
}
