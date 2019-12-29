package dev.grack.matchschedulefootbal

import com.google.android.material.tabs.TabLayout
import androidx.test.espresso.Espresso
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class NavigationActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(NavigationActivity::class.java)

    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() = Matchers.allOf(ViewMatchers.isDisplayed(), ViewMatchers.isAssignableFrom(TabLayout::class.java))

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                        ?: throw PerformException.Builder()
                                .withCause(Throwable("No tab at index $tabIndex"))
                                .build()

                tabAtIndex.select()
            }
        }
    }

    @Test
    fun selectTabPastMatch() {
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.tabs_draw)).perform(selectTabAtPosition(1))

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.recycler_past)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.recycler_past)).perform(RecyclerViewActions.scrollToPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(10))
        Espresso.onView(ViewMatchers.withId(R.id.recycler_past)).perform(RecyclerViewActions.actionOnItemAtPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(10, ViewActions.click()))

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())

        Thread.sleep(2000)
        Espresso.pressBack()

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.tabs_draw)).perform(selectTabAtPosition(2))

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.list_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())

        Thread.sleep(2000)
        Espresso.pressBack()

        Thread.sleep(2000)
    }
}