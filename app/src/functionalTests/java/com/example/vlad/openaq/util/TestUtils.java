package com.example.vlad.openaq.util;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;

import com.example.vlad.openaq.App;
import com.example.vlad.openaq.network.ChangeableBaseUrl;

import static com.example.vlad.openaq.Constants.DO_NOT_CREATE_INSTANCE_OF_THIS_CLASS;

public final class TestUtils {

    public static ChangeableBaseUrl getChangeableBaseUrl() {
        return ((App) InstrumentationRegistry.getTargetContext().getApplicationContext())
                .getAppComponent()
                .changeableBaseUrl();
    }

    public static Resources getResources() {
        return InstrumentationRegistry.getTargetContext().getResources();
    }

    private TestUtils() {
        throw new IllegalStateException(DO_NOT_CREATE_INSTANCE_OF_THIS_CLASS);
    }
}
