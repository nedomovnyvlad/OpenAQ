package com.example.vlad.openaq.util;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;

import com.example.vlad.openaq.App;

import static com.example.vlad.openaq.Constants.DO_NOT_CREATE_INSTANCE_OF_THIS_CLASS;

public class TestUtils {

    public static App app() {
        return (App) InstrumentationRegistry.getTargetContext().getApplicationContext();
    }

    public static Resources getResources() {
        return InstrumentationRegistry.getTargetContext().getResources();
    }

    private TestUtils() {
        throw new IllegalStateException(DO_NOT_CREATE_INSTANCE_OF_THIS_CLASS);
    }
}
