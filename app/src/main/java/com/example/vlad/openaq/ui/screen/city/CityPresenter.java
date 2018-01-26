package com.example.vlad.openaq.ui.screen.city;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.vlad.openaq.entity.CityInfo;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

@InjectViewState
public class CityPresenter extends MvpPresenter<CityView> {
    public static final String TAG = CityPresenter.class.getSimpleName();

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
                        Log.e(TAG, "Failed to get cities", e);
                    }
                });
    }
}
