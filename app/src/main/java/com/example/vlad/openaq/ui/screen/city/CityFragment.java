package com.example.vlad.openaq.ui.screen.city;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.vlad.openaq.R;
import com.example.vlad.openaq.entity.CityInfo;
import com.example.vlad.openaq.ui.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class CityFragment extends BaseFragment implements CityView {

    @Inject
    @InjectPresenter
    CityPresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private CityAdapter adapter;

    public static CityFragment newInstance() {
        return new CityFragment();
    }

    @ProvidePresenter
    public CityPresenter providePresenter() {
        return presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new CityAdapter(getContext());
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
                getContext(),
                layoutManager.getOrientation()
        );

        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showList(List<CityInfo> cityInfoList) {
        adapter.setData(cityInfoList);
    }
}
