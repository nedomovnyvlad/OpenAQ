package com.example.vlad.openaq.ui.screen.city;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.vlad.openaq.entity.CityInfo;
import com.example.vlad.openaq.moxy.AddToEndSingleByTagStateStrategy;

import java.util.List;

public interface CityView extends MvpView {
    String CITY_LIST_TAG = "CITY_LIST_TAG";

    @StateStrategyType(value = AddToEndSingleByTagStateStrategy.class, tag = CITY_LIST_TAG)
    void showList(List<CityInfo> cityInfoList);

    @StateStrategyType(value = AddToEndSingleByTagStateStrategy.class, tag = CITY_LIST_TAG)
    void showNetworkError();

    @StateStrategyType(value = AddToEndSingleByTagStateStrategy.class, tag = CITY_LIST_TAG)
    void showLoading();

    @StateStrategyType(SkipStrategy.class)
    void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter);

    @StateStrategyType(SkipStrategy.class)
    void unregisterReceiver(BroadcastReceiver broadcastReceiver);
}
