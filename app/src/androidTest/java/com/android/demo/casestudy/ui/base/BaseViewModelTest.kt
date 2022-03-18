package com.android.demo.casestudy.ui.base

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.SmallTest
import com.android.demo.casestudy.MainActivity
import com.android.demo.casestudy.R
import com.android.demo.casestudy.data.EspressoIdlingResource
import com.android.demo.casestudy.data.FakeCaseStudy
import com.android.demo.casestudy.repository.BaseRepository
import com.android.demo.casestudy.response.CaseStudies
import com.android.demo.casestudy.response.CaseStudyResponse
import com.android.demo.casestudy.ui.MainViewHolder
import com.android.demo.casestudy.utils.getOrAwaitValue
import com.bumptech.glide.load.engine.Resource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@SmallTest
@HiltAndroidTest
class BaseViewModelTest  {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Inject
    lateinit var baseRepository: BaseRepository

    lateinit var viewModel :BaseViewModel

    private val test_item = 2

    @Before
    fun init(){
        hiltRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        viewModel = BaseViewModel(baseRepository)

    }
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun test_appLaunch_isMainFragmentVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview_casestudy))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.progressbar_casestudy))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }
    @Test
    fun `test_isDataLoad_in_adapter`(){

        Espresso.onView(ViewMatchers.withId(R.id.recyclerview_casestudy))
            .perform(RecyclerViewActions.scrollToPosition<MainViewHolder>(test_item))

        Espresso.onView(ViewMatchers.withId(R.id.recyclerview_casestudy))
            .check(
                ViewAssertions.matches(
                    withViewAtPosition(
                        test_item, ViewMatchers.hasDescendant(
                            AllOf.allOf(
                                ViewMatchers.withId(R.id.text_title),
                                ViewMatchers.isDisplayed()
                            )
                        )
                    )
                )
            )

        Espresso.onView(ViewMatchers.withId(R.id.recyclerview_casestudy))
            .check(
                ViewAssertions.matches(
                    withViewAtPosition(
                        test_item, ViewMatchers.hasDescendant(
                            AllOf.allOf(
                                ViewMatchers.withId(R.id.text_teaser),
                                ViewMatchers.isDisplayed()
                            )
                        )
                    )
                )
            )

        Espresso.onView(ViewMatchers.withId(R.id.recyclerview_casestudy))
            .check(
                ViewAssertions.matches(
                    withViewAtPosition(
                        test_item, ViewMatchers.hasDescendant(
                            AllOf.allOf(
                                ViewMatchers.withId(R.id.image_hero),
                                ViewMatchers.isDisplayed()
                            )
                        )
                    )
                )
            )

        Espresso.onView(ViewMatchers.withId(R.id.recyclerview_casestudy))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MainViewHolder>(
                    test_item,
                    ViewActions.click()
                )
            )

    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Test
    fun `test_viewmodel_data`(){
        var caseStudiesList : List<CaseStudies> = FakeCaseStudy.caseStudy.toList()

         val _caseStudyResponse =
             CaseStudyResponse(caseStudiesList as ArrayList<CaseStudies>)

        viewModel.setCaseResponse(_caseStudyResponse)
        val result = viewModel.caseStudyResponse.getOrAwaitValue().run {
           assert(true)
        }
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