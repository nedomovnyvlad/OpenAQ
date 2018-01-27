package com.example.vlad.openaq.ui.screen.city;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.vlad.openaq.entity.CityInfo;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

@InjectViewState
public class CityPresenter extends MvpPresenter<CityView> {

    private final CityModel cityModel;

    public CityPresenter(CityModel cityModel) {
        this.cityModel = cityModel;
    }

    @Override
    protected void onFirstViewAttach() {
        cityModel.getCities()
                .subscribe(new DisposableSingleObserver<List<CityInfo>>() {
                    @Override
                    public void onSuccess(List<CityInfo> cityInfoList) {
                        getViewState().showList(cityInfoList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Failed to get cities");
                    }
                });
    }
}
