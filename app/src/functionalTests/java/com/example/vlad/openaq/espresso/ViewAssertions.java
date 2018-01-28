package com.example.vlad.openaq.espresso;

import android.support.annotation.NonNull;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;

import static com.example.vlad.openaq.Constants.DO_NOT_CREATE_INSTANCE_OF_THIS_CLASS;

public final class ViewAssertions {

    @NonNull
    public static ViewAssertion recyclerViewShouldHaveItemsCount(int count) {
        return (view, noViewFoundException) -> {
            final RecyclerView recyclerView = (RecyclerView) view;
            final int actualCount = recyclerView.getAdapter().getItemCount();

            if (actualCount != count) {
                throw new AssertionError("RecyclerView has " + actualCount + " while expected " + count);
            }
        };
    }

    private ViewAssertions() {
        throw new IllegalStateException(DO_NOT_CREATE_INSTANCE_OF_THIS_CLASS);
    }
}
