package com.example.vlad.openaq.api;

import com.example.vlad.openaq.App;
import com.example.vlad.openaq.entity.CityInfo;
import com.example.vlad.openaq.entity.CityResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.HttpException;

import static com.example.vlad.openaq.Constants.Api.MIN_CITY_COUNT;
import static com.example.vlad.openaq.util.TestUtils.CITY_RESPONSE_JSON;
import static com.example.vlad.openaq.util.TestUtils.getAppComponent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

// TODO: 1/29/18 remove sdk after it starts to work without it
@Config(application = App.class, sdk = 26)
@RunWith(RobolectricTestRunner.class)
public class CityServiceTest {

    private MockWebServer mockWebServer;

    private CityService cityService;

    @Before
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        cityService = getAppComponent().cityService();

        mockWebServer.start();

        getAppComponent()
                .changeableBaseUrl()
                .setUrl(mockWebServer.url("").toString());
    }

    @After
    public void afterTest() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void cityService_shouldTakeCorrectResponse() {
        mockWebServer.enqueue(new MockResponse().setBody(CITY_RESPONSE_JSON));

        List<CityInfo> cityInfoList = cityService.getCities()
                .map(CityResponse::results)
                .blockingGet();

        assertEquals(cityInfoList.size(), 2);
        assertTrue(cityInfoList.get(0).count() == MIN_CITY_COUNT + 1);
        assertTrue(cityInfoList.get(1).count() == MIN_CITY_COUNT + 2);
        assertEquals(cityInfoList.get(0).name(), "Name 1");
        assertEquals(cityInfoList.get(1).name(), "Name 2");
    }

    @Test
    public void cityService_shouldThrowIfGetsIncorrectResponse() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404));

        try {
            cityService.getCities().blockingGet();
            fail("HttpException should be thrown");
        } catch (HttpException expected) {
        }
    }
}
