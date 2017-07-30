package com.youknow.baking;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

    @Test
    public void fetchRecipesTest1() throws Exception {
        onView(withId(R.id.rv_recipes)).check(matches(isCompletelyDisplayed()));

        onView(withId(R.id.rv_recipes)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(allOf(instanceOf(TextView.class), withParent(isAssignableFrom(Toolbar.class)))).check(matches(withText("Nutella Pie")));
    }

    @Test
    public void fetchRecipesTest2() throws Exception {
        onView(withId(R.id.rv_recipes)).check(matches(isCompletelyDisplayed()));

        onView(withId(R.id.rv_recipes)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(allOf(instanceOf(TextView.class), withParent(isAssignableFrom(Toolbar.class)))).check(matches(withText("Brownies")));
    }

    @Test
    public void fetchRecipesTest3() throws Exception {
        onView(withId(R.id.rv_recipes)).check(matches(isCompletelyDisplayed()));

        onView(withId(R.id.rv_recipes)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(allOf(instanceOf(TextView.class), withParent(isAssignableFrom(Toolbar.class)))).check(matches(withText("Yellow Cake")));
    }

    @Test
    public void fetchRecipesTest4() throws Exception {
        onView(withId(R.id.rv_recipes)).check(matches(isCompletelyDisplayed()));

        onView(withId(R.id.rv_recipes)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(allOf(instanceOf(TextView.class), withParent(isAssignableFrom(Toolbar.class)))).check(matches(withText("Cheesecake")));
    }

}
