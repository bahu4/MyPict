package ru.bahu.mypict

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.bahu.mypict.ui.detail.DetailActivity
import ru.bahu.mypict.ui.favorites.FavoritesActivity
import ru.bahu.mypict.ui.main.MainActivity
import java.lang.IllegalArgumentException


@RunWith(AndroidJUnit4::class)
class MainActivityUITest {
    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun isActivityCreatedWhenFabIsClicked() {
        Intents.init()
        onView(withId(R.id.fab)).perform(click())
        intended(hasComponent(FavoritesActivity::class.java.name))
        Intents.release()
    }

    @Test
    fun checkFabIsDisplayed() {
        onView(withId(R.id.fab))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkAppBarIsDisplayed() {
        onView(withId(R.id.appbar))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkAppBarImageViewIsDisplayed() {
        onView(withId(R.id.appbar_image_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkToolbarIsDisplayed() {
        onView(withId(R.id.my_toolbar))
            .check(matches(isDisplayed()))
    }

    @Test
    fun mainRecyclerTestItemClicked() {
        Intents.init()
        try {
            Thread.sleep(3000)
            onView(withId(R.id.main_rv))
                .perform(
                    RecyclerViewActions
                        .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
                )
            intended(hasComponent(DetailActivity::class.java.name))

        } catch (e: IllegalArgumentException) {

        } finally {
            Intents.release()
        }
    }
}