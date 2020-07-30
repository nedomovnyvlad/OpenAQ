package com.example.vlad.openaq.retrofit;

import android.support.annotation.NonNull;

import com.example.vlad.openaq.network.NetworkChecker;

import java.lang.reflect.Type;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.CallAdapter;

public class RxCallAdapterWrapper<R> implements CallAdapter<R, Object> {
    private final CallAdapter<R, Object> wrapped;
    private final NetworkChecker networkChecker;

    public RxCallAdapterWrapper(CallAdapter<R, Object> wrapped, NetworkChecker networkChecker) {
        this.wrapped = wrapped;
        this.networkChecker = networkChecker;
    }

    @Override
    public Type responseType() {
        return wrapped.responseType();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object adapt(@NonNull Call<R> call) {
        Object adaptedByWrapped = wrapped.adapt(call);
        if (adaptedByWrapped instanceof Single) {
            return ((Single<R>) adaptedByWrapped)
                    .doOnSubscribe(ignored -> {
                        if (!networkChecker.isNetworkAvailable()) {
                            throw new NoConnectivityException();
                        }
                    });
        } else {
            throw new IllegalStateException("Unable to adapt " + adaptedByWrapped.getClass().toString());
        }
    }
}
