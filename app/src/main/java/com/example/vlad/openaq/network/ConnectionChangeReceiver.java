package com.example.vlad.openaq.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.vlad.openaq.util.NetworkUtils;

public class ConnectionChangeReceiver extends BroadcastReceiver {

    private ConnectionListener connectionListener;

    public ConnectionChangeReceiver(ConnectionListener connectionListener) {
        this.connectionListener = connectionListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (NetworkUtils.isNetworkAvailable(context)) {
            connectionListener.onConnectionAppear();
        }
    }
}
