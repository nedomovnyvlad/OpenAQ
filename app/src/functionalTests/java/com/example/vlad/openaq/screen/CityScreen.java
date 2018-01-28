package com.example.vlad.openaq.screen;

import android.support.annotation.NonNull;
import android.support.test.espresso.matcher.ViewMatchers;

import com.example.vlad.openaq.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.vlad.openaq.espresso.ViewActions.noOp;
import static com.example.vlad.openaq.espresso.ViewAssertions.recyclerViewShouldHaveItemsCount;
import static com.example.vlad.openaq.util.TestUtils.getResources;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

public class CityScreen {

    public CityScreen shouldDisplayExpectedAmountOfItems(int count) {
        onView(ViewMatchers.withId(R.id.recycler_view)).check(recyclerViewShouldHaveItemsCount(count));
        return this;
    }

    public CityScreen shouldDisplayItemWithName(@NonNull String title) {
        onView(withId(R.id.recycler_view))
                .perform(actionOnItem(hasDescendant(
                        allOf(
                                withId(R.id.textview_name),
                                withText(title))),
                        noOp()));
        return this;
    }

    public CityScreen shouldDisplayItemWithCount(int count) {
        onView(withId(R.id.recycler_view))
                .perform(actionOnItem(hasDescendant(
                        allOf(
                                withId(R.id.textview_count),
                                withText(getResources().getString(R.string.item_measurements, count)))),
                        noOp()));
        return this;
    }

    public CityScreen shouldNotDisplayItems() {
        onView(withId(R.id.recycler_view)).check(matches(not(isDisplayed())));
        return this;
    }

    public CityScreen shouldDisplayErrorText() {
        onView(withId(R.id.textview_error_message)).check(matches(isDisplayed()));
        return this;
    }

    public CityScreen shouldNotDisplayErrorText() {
        onView(withId(R.id.textview_error_message)).check(matches(not(isDisplayed())));
        return this;
    }

    public CityScreen shouldDisplayRepeatRequestButton() {
        onView(withId(R.id.button_repeat_request)).check(matches(isDisplayed()));
        return this;
    }

    public CityScreen shouldNotDisplayRepeatRequestButton() {
        onView(withId(R.id.button_repeat_request)).check(matches(not(isDisplayed())));
        return this;
    }

    public CityScreen clickOnRepeatRequestButton() {
        onView(withId(R.id.button_repeat_request)).perform(click());
        return this;
    }
}
