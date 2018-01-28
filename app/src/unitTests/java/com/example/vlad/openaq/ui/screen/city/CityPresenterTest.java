package com.example.vlad.openaq.ui.screen.city;

import com.example.vlad.openaq.entity.CityInfo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Single;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CityPresenterTest {

    @Mock
    private CityModel cityModel;

    @Mock
    private CityView$$State cityViewState;

    private CityPresenter cityPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cityPresenter = new CityPresenter(cityModel);
        cityPresenter.setViewState(cityViewState);
    }

    @Test
    public void onFirstViewAttach_shouldShowList() {
        List<CityInfo> cityInfoList = prepareList();

        cityPresenter.onFirstViewAttach();

        verifyListShown(cityInfoList);
    }

    @Test
    public void onFirstViewAttach_shouldShowErrorIfExceptionHappened() {
        prepareError();

        cityPresenter.onFirstViewAttach();

        verifyErrorShown();
    }

    @Test
    public void requestCityInfoList_shouldShowList() {
        List<CityInfo> cityInfoList = prepareList();

        cityPresenter.requestCityInfoList();

        verifyListShown(cityInfoList);
    }

    @Test
    public void requestCityInfoList_shouldShowErrorIfExceptionHappened() {
        prepareError();

        cityPresenter.requestCityInfoList();

        verifyErrorShown();
    }

    private List<CityInfo> prepareList() {
        List<CityInfo> cityInfoList = asList(
                CityInfo.create("City1", 10000),
                CityInfo.create("City2", 20000)
        );
        when(cityModel.getCities()).thenReturn(Single.just(cityInfoList));

        return cityInfoList;
    }

    private void prepareError() {
        when(cityModel.getCities()).thenReturn(Single.error(new Exception()));
    }

    private void verifyListShown(List<CityInfo> cityInfoList) {
        verify(cityViewState).showLoading();
        verify(cityViewState).showList(cityInfoList);
    }

    private void verifyErrorShown() {
        verify(cityViewState).showLoading();
        verify(cityViewState).showNetworkError();
    }
}
