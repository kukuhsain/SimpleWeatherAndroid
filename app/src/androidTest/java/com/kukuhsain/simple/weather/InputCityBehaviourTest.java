package com.kukuhsain.simple.weather;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.kukuhsain.simple.weather.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by kukuh on 14/12/16.
 */
@RunWith(AndroidJUnit4.class)
public class InputCityBehaviourTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void inputCity() throws Exception {
        onView(withId(R.id.btn_insert_city))
                .perform(click());
        onView(withText("Insert City"))
                .check(matches(isDisplayed()));
        onView(withId(R.id.et_city))
                .perform(typeText("sleman"));
        onView(withText("OK"))
                .perform(click());
    }
}
