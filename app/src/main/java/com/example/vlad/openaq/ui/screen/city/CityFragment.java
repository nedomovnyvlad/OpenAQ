package com.example.vlad.openaq.ui.screen.city;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.vlad.openaq.R;
import com.example.vlad.openaq.entity.CityInfo;
import com.example.vlad.openaq.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static java.util.Objects.requireNonNull;

public class CityFragment extends BaseFragment implements CityView {

    @Inject
    @InjectPresenter
    CityPresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.layout_network_error)
    View networkErrorView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private CityAdapter adapter = new CityAdapter();

    private final List<BroadcastReceiver> broadcastReceivers = new ArrayList<>();

    public static CityFragment newInstance() {
        return new CityFragment();
    }

    @ProvidePresenter
    public CityPresenter providePresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                requireNonNull(getContext()),
                layoutManager.getOrientation()
        );

        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        for (BroadcastReceiver broadcastReceiver : broadcastReceivers) {
            requireNonNull(getContext()).unregisterReceiver(broadcastReceiver);
        }

        super.onPause();
    }

    @OnClick(R.id.button_repeat_request)
    public void onRepeatRequestButtonClick() {
        presenter.requestCityInfoList();
    }

    @Override
    public void showList(List<CityInfo> cityInfoList) {
        networkErrorView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        adapter.setData(cityInfoList);
    }

    @Override
    public void showNetworkError() {
        networkErrorView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        networkErrorView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        requireNonNull(getContext()).registerReceiver(broadcastReceiver, intentFilter);
        broadcastReceivers.add(broadcastReceiver);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        requireNonNull(getContext()).unregisterReceiver(broadcastReceiver);
        broadcastReceivers.remove(broadcastReceiver);
    }
}
