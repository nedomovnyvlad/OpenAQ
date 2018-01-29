package com.example.vlad.openaq.network;

import android.content.Context;

import com.example.vlad.openaq.util.NetworkUtils;

public class NetworkChecker {

    private final Context context;

    public NetworkChecker(Context context) {
        this.context = context;
    }

    public boolean isOnline() {
        return NetworkUtils.isOnline(context);
    }
}
