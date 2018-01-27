package com.example.vlad.openaq.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.vlad.openaq.Constants;

public final class NetworkUtils {

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private NetworkUtils() {
        throw new IllegalStateException(Constants.DO_NOT_CREATE_INSTANCE_OF_THIS_CLASS);
    }
}
