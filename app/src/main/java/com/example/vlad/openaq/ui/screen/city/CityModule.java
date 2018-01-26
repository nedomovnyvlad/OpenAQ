package com.example.vlad.openaq.ui.screen.city;

import android.support.annotation.NonNull;

import com.example.vlad.openaq.api.CityService;

import dagger.Module;
import dagger.Provides;

@Module
public class CityModule {

    @Provides
    @NonNull
    public CityModel provideCityModel(CityService cityService) {
        return new CityModel(cityService);
    }

    @Provides
    @NonNull
    public CityPresenter provideCityPresenter(CityModel cityModel) {
        return new CityPresenter(cityModel);
    }
}
