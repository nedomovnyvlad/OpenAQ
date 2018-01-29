package com.example.vlad.openaq.espresso;

import android.support.annotation.NonNull;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import static com.example.vlad.openaq.Constants.DO_NOT_CREATE_INSTANCE_OF_THIS_CLASS;

public final class ViewActions {

    @NonNull
    public static ViewAction noOp() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "no-op";
            }

            @Override
            public void perform(UiController uiController, View view) {
                // no-op
            }
        };
    }

    private ViewActions() {
        throw new IllegalStateException(DO_NOT_CREATE_INSTANCE_OF_THIS_CLASS);
    }
}
