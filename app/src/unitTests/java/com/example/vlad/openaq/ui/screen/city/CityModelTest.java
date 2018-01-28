package com.example.vlad.openaq.ui.screen.city;

import com.example.vlad.openaq.api.CityService;
import com.example.vlad.openaq.entity.CityInfo;
import com.example.vlad.openaq.entity.CityResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static com.example.vlad.openaq.Constants.Api.MIN_CITY_COUNT;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Fail.failBecauseExceptionWasNotThrown;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CityModelTest {

    @Mock
    private CityService cityService;

    private CityModel cityModel;

    private final List<CityInfo> listWithCountGreaterOrEqualsMin = unmodifiableList(
            asList(
                    CityInfo.create("City", MIN_CITY_COUNT),
                    CityInfo.create("City", MIN_CITY_COUNT + 100),
                    CityInfo.create("City", MIN_CITY_COUNT + 200),
                    CityInfo.create("City", MIN_CITY_COUNT + 300)
            )
    );

    private final List<CityInfo> listWithCountLessThanMin = unmodifiableList(
            asList(
                    CityInfo.create("City", MIN_CITY_COUNT - 100),
                    CityInfo.create("City", MIN_CITY_COUNT - 200),
                    CityInfo.create("City", MIN_CITY_COUNT - 300)
            )
    );

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());

        cityModel = new CityModel(cityService);
    }

    @Test
    public void getCities_shouldReturnListInDescOrderByCount() {
        List<CityInfo> resultList =
                getModifiedListFromModel(listWithCountGreaterOrEqualsMin);

        List<CityInfo> expectedList = new ArrayList<>(resultList);
        Collections.sort(expectedList, new CityInfo.DescByCountComparator());

        assertEquals(expectedList, resultList);
    }

    @Test
    public void getCities_shouldReturnListWithGreaterOrEqualsMinCount() {
        List<CityInfo> startList = new ArrayList<>();
        startList.addAll(listWithCountGreaterOrEqualsMin);
        startList.addAll(listWithCountLessThanMin);

        List<CityInfo> resultList = getModifiedListFromModel(startList);

        List<CityInfo> expectedList = new ArrayList<>(listWithCountGreaterOrEqualsMin);

        assertTrue(resultList.containsAll(expectedList));
        assertTrue(expectedList.containsAll(resultList));
    }

    private List<CityInfo> getModifiedListFromModel(List<CityInfo> cityInfoList) {
        CityResponse cityResponse = mock(CityResponse.class);
        when(cityResponse.results()).thenReturn(cityInfoList);
        when(cityService.getCities()).thenReturn(Single.just(cityResponse));

        return cityModel.getCities().blockingGet();
    }

    @Test
    public void getCities_shouldReturnErrorFromCityService() {
        Exception error = new RuntimeException();
        when(cityService.getCities()).thenReturn(Single.error(error));

        try {
            cityModel.getCities().blockingGet();
            failBecauseExceptionWasNotThrown(RuntimeException.class);
        } catch (Exception expected) {
            assertThat(expected).isSameAs(error);
        }
    }
}
