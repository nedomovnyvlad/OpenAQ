package com.example.vlad.openaq.entity;

import com.example.vlad.openaq.App;
import com.example.vlad.openaq.util.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.List;

import static com.example.vlad.openaq.Constants.Api.MIN_CITY_COUNT;
import static com.example.vlad.openaq.util.TestUtils.CITY_RESPONSE_JSON;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Config(application = App.class)
@RunWith(RobolectricTestRunner.class)
public class CityResponseTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = TestUtils.getAppComponent().objectMapper();
    }

    @Test
    public void cityResponse_shouldBeDeserializableFromJson() throws IOException {
        CityResponse cityResponse = objectMapper.readValue(CITY_RESPONSE_JSON, CityResponse.class);

        List<CityInfo> cityInfoList = cityResponse.results();
        assertEquals(cityInfoList.size(), 2);
        assertTrue(cityInfoList.get(0).count() == MIN_CITY_COUNT + 1);
        assertTrue(cityInfoList.get(1).count() == MIN_CITY_COUNT + 2);
        assertEquals(cityInfoList.get(0).name(), "Name 1");
        assertEquals(cityInfoList.get(1).name(), "Name 2");
    }
}
