package com.example.vlad.openaq.ui.screen.city;

import com.example.vlad.openaq.api.CityService;
import com.example.vlad.openaq.entity.CityInfo;
import com.example.vlad.openaq.entity.CityResponse;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.vlad.openaq.Constants.Api.MIN_CITY_COUNT;

public class CityModel {

    private final CityService cityService;

    public CityModel(CityService cityService) {
        this.cityService = cityService;
    }

    public Single<List<CityInfo>> getCities() {
        return cityService.getCities()
                .map(CityResponse::results)
                .flattenAsObservable(cityInfoList -> cityInfoList)
                .filter(cityInfo -> cityInfo.count() >= MIN_CITY_COUNT)
                .toSortedList(new CityInfo.DescByCountComparator())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
