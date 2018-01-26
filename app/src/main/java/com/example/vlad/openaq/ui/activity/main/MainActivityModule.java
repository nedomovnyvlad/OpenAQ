package com.example.vlad.openaq.ui.activity.main;


import com.example.vlad.openaq.di.scope.FragmentScope;
import com.example.vlad.openaq.ui.screen.city.CityFragment;
import com.example.vlad.openaq.ui.screen.city.CityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = {CityModule.class})
    CityFragment cityFragment();
}
