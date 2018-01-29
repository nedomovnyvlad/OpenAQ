package com.example.vlad.openaq.ui.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends MvpAppCompatFragment {

    @NonNull
    private Unbinder unbinder = Unbinder.EMPTY;

    @Override
    @CallSuper
    public void onCreate(Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    @CallSuper
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }
}

