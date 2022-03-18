package com.android.demo.casestudy.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.android.demo.casestudy.MainActivity
import com.android.demo.casestudy.R
import com.android.demo.casestudy.data.EspressoIdlingResource
import com.android.demo.casestudy.data.FakeCaseStudy
import com.android.demo.casestudy.response.CaseStudies
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@HiltAndroidTest
@RunWith(JUnit4::class)
class MainFragmentTest{
    // To run this test case: please change in build.gradle file
    //to testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val test_item = 2
    val case_list : CaseStudies = FakeCaseStudy.caseStudy[test_item]


    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun test_appLaunch_isMainFragmentVisible() {
        onView(withId(R.id.recyclerview_casestudy)).check(matches(isDisplayed()));

        onView(withId(R.id.progressbar_casestudy)).check(matches(not(isDisplayed())))
    }

    @Test
    fun `test_isDataLoad_in_adapter`(){

        onView(withId(R.id.recyclerview_casestudy))
            .perform(scrollToPosition<MainViewHolder>(test_item))

        onView(withId(R.id.recyclerview_casestudy))
            .check(matches(withViewAtPosition(test_item, hasDescendant(allOf(withId(R.id.text_title),
            isDisplayed())))))

        onView(withId(R.id.recyclerview_casestudy))
            .check(matches(withViewAtPosition(test_item, hasDescendant(allOf(withId(R.id.text_teaser),
                isDisplayed())))))

        onView(withId(R.id.recyclerview_casestudy))
            .check(matches(withViewAtPosition(test_item, hasDescendant(allOf(withId(R.id.image_hero),
                isDisplayed())))))

        onView(withId(R.id.recyclerview_casestudy))
            .perform(actionOnItemAtPosition<MainViewHolder>(test_item, click()))

    }

    private fun withViewAtPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}