package com.example.vlad.openaq.ui.screen.city;

import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.vlad.openaq.entity.CityInfo;
import com.example.vlad.openaq.network.ConnectionChangeReceiver;
import com.example.vlad.openaq.network.ConnectionListener;
import com.example.vlad.openaq.retrofit.NoConnectivityException;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

@InjectViewState
public class CityPresenter extends MvpPresenter<CityView> implements ConnectionListener {

    private final CityModel cityModel;

    private ConnectionChangeReceiver connectionChangeReceiver;

    public CityPresenter(CityModel cityModel) {
        this.cityModel = cityModel;
    }

    @Override
    protected void onFirstViewAttach() {
        requestCityInfoList();
    }

    public void requestCityInfoList() {
        getViewState().showLoading();

        cityModel.getCities()
                .subscribe(new DisposableSingleObserver<List<CityInfo>>() {
                    @Override
                    public void onSuccess(List<CityInfo> cityInfoList) {
                        getViewState().showList(cityInfoList);
                        unregisterConnectionReceiver();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Failed to get cities");
                        if (e instanceof NoConnectivityException) {
                            registerConnectionReceiver();
                        }
                        getViewState().showNetworkError();
                    }
                });
    }

    private void registerConnectionReceiver() {
        if (connectionChangeReceiver == null) {
            IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            connectionChangeReceiver = new ConnectionChangeReceiver(this);
            getViewState().registerReceiver(connectionChangeReceiver, intentFilter);
        }
    }

    private void unregisterConnectionReceiver() {
        if (connectionChangeReceiver != null) {
            getViewState().unregisterReceiver(connectionChangeReceiver);
            connectionChangeReceiver = null;
        }
    }

    @Override
    public void onConnectionAppear() {
        requestCityInfoList();
    }
}
