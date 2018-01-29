package com.example.vlad.openaq.ui.screen.city;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.vlad.openaq.entity.CityInfo;
import com.example.vlad.openaq.moxy.AddToEndSingleByTagStateStrategy;
import com.example.vlad.openaq.moxy.SkipRemoveByTagStrategy;

import java.util.List;

public interface CityView extends MvpView {
    String CITY_LIST_TAG = "CITY_LIST_TAG";
    String CONNECTION_RECEIVER_TAG = "CONNECTION_RECEIVER_TAG";

    @StateStrategyType(value = AddToEndSingleByTagStateStrategy.class, tag = CITY_LIST_TAG)
    void showList(List<CityInfo> cityInfoList);

    @StateStrategyType(value = AddToEndSingleByTagStateStrategy.class, tag = CITY_LIST_TAG)
    void showNetworkError();

    @StateStrategyType(value = AddToEndSingleByTagStateStrategy.class, tag = CITY_LIST_TAG)
    void showLoading();

    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = CONNECTION_RECEIVER_TAG)
    void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter);

    @StateStrategyType(value = SkipRemoveByTagStrategy.class, tag = CONNECTION_RECEIVER_TAG)
    void unregisterReceiver(BroadcastReceiver broadcastReceiver);
}
