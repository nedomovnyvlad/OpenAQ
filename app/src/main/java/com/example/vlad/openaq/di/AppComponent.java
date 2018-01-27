package com.example.vlad.openaq.di;

import android.app.Application;

import com.example.vlad.openaq.App;
import com.example.vlad.openaq.di.module.ApplicationModule;
import com.example.vlad.openaq.di.module.ContextModule;
import com.example.vlad.openaq.di.module.RetrofitModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ContextModule.class,
        RetrofitModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}
