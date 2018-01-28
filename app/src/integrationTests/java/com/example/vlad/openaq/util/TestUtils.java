package com.example.vlad.openaq.util;

import com.example.vlad.openaq.App;
import com.example.vlad.openaq.di.AppComponent;

import org.robolectric.RuntimeEnvironment;

import static com.example.vlad.openaq.Constants.Api.MIN_CITY_COUNT;
import static com.example.vlad.openaq.Constants.DO_NOT_CREATE_INSTANCE_OF_THIS_CLASS;

public final class TestUtils {

    public static final String CITY_RESPONSE_JSON = "{\n" +
            "   \"meta\":{\n" +
            "      \"name\":\"openaq-api\",\n" +
            "      \"license\":\"CC BY 4.0\",\n" +
            "      \"website\":\"https://docs.openaq.org/\",\n" +
            "      \"page\":1,\n" +
            "      \"limit\":2,\n" +
            "      \"found\":2021\n" +
            "   },\n" +
            "   \"results\":[\n" +
            "      {\n" +
            "         \"city\":\"Name 1\",\n" +
            "         \"country\":\"AD\",\n" +
            "         \"locations\":2,\n" +
            "         \"count\":" + (MIN_CITY_COUNT + 1) + "\n" +
            "      },\n" +
            "      {\n" +
            "         \"city\":\"Name 2\",\n" +
            "         \"country\":\"AD\",\n" +
            "         \"locations\":1,\n" +
            "         \"count\":" + (MIN_CITY_COUNT + 2) + "\n" +
            "      }\n" +
            "   ]\n" +
            "}";

    public static AppComponent getAppComponent() {
        return ((App) RuntimeEnvironment.application).getAppComponent();
    }

    private TestUtils() {
        throw new IllegalStateException(DO_NOT_CREATE_INSTANCE_OF_THIS_CLASS);
    }
}

