package com.example.vlad.openaq.ui.screen.city;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.vlad.openaq.entity.CityInfo;

import java.util.List;

public interface CityView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showList(List<CityInfo> cityInfoList);
}
