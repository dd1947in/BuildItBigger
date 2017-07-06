package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by dd2568 on 7/5/2017.
 * The following Espresso Test starts main activity,  Asserts that "button_tell_joke" is displayed
 * Performs the "click()" on the button
 * Asserts that view "tv_joke_text_view_al" from child activity / joke activity / activity from android library "jokeactivity" is present
 * Asserts that the view has joke/text is populated.
 *
 */
@RunWith(AndroidJUnit4.class)
public class AsyncTaskJokeLoadingTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void getAJokeAndTestViewInJokeActivity() {

        //Assert MainActivity is displayed with id button_tell_joke
        onView(withId(R.id.button_tell_joke)).check(matches(isDisplayed()));

        //Perform click on the button with id button_tell_joke
        onView(withId(R.id.button_tell_joke)).perform(click());

        //Assert that Joke Activity is loaded with a TextView id tv_joke_text_view_al
        onView(withId(R.id.tv_joke_text_view_al)).check(matches(isDisplayed()));

        //Assert that Joke Text is not equal ""
        onView(withId(R.id.tv_joke_text_view_al)).check(matches(not(withText(""))));

    }
}
