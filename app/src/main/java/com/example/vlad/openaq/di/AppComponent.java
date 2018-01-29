package com.example.vlad.openaq.di;

import android.app.Application;

import com.example.vlad.openaq.App;
import com.example.vlad.openaq.api.CityService;
import com.example.vlad.openaq.di.module.ApplicationModule;
import com.example.vlad.openaq.di.module.ContextModule;
import com.example.vlad.openaq.di.module.NetworkModule;
import com.example.vlad.openaq.di.module.RetrofitModule;
import com.example.vlad.openaq.network.ChangeableBaseUrl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ContextModule.class,
        NetworkModule.class,
        RetrofitModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        Builder networkModule(NetworkModule networkModule);

        AppComponent build();
    }

    ChangeableBaseUrl changeableBaseUrl();

    ObjectMapper objectMapper();

    CityService cityService();

    void inject(App app);
}
