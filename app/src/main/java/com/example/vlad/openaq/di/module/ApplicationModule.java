package com.example.vlad.openaq.di.module;

import com.example.vlad.openaq.di.scope.ActivityScope;
import com.example.vlad.openaq.ui.activity.main.MainActivity;
import com.example.vlad.openaq.ui.activity.main.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidSupportInjectionModule.class})
public interface ApplicationModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    MainActivity mainActivityInjector();
}
