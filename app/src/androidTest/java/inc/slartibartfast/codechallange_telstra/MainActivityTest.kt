package inc.slartibartfast.codechallange_telstra

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private fun onViewCheckAssertionWithRetries(matcher: Matcher<View>, assertion: ViewAssertion, retries: Int = 100, delay: Long = 100): ViewInteraction {
        repeat(retries) { attempt ->
            try {
                return onView(matcher).check(assertion)
            } catch (e: NoMatchingViewException) {
                if (attempt >= retries) {
                    throw e
                } else {
                    Thread.sleep(delay)
                }
            }
        }

        throw AssertionError("Failed to find match for $matcher")
    }

    private fun scrollViewToMatcher(recyclerViewId: Int, matcher: Matcher<View>) {
        onView(withId(recyclerViewId)).perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(matcher))
        )

        onView(matcher).check(matches(isDisplayed()))
    }

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setTestData() {
        activityRule.scenario.onActivity {
            it.setApiInterface(true)
        }
    }

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.swipeContainer)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun test_checkTitle() {
        onViewCheckAssertionWithRetries(withText("About Canada (refresh 0)"), matches(isDisplayed()))
    }

    @Test
    fun test_checkTiles() {
        onViewCheckAssertionWithRetries(withText("About Canada (refresh 0)"), matches(isDisplayed()))

        scrollViewToMatcher(R.id.recyclerView, withText("Beavers"))
        scrollViewToMatcher(R.id.recyclerView, withText("Flag"))
        scrollViewToMatcher(R.id.recyclerView, withText("Transportation"))
        scrollViewToMatcher(R.id.recyclerView, withText("Hockey Night in Canada"))
        scrollViewToMatcher(R.id.recyclerView, withText("Eh"))
        scrollViewToMatcher(R.id.recyclerView, withText("Housing"))
        scrollViewToMatcher(R.id.recyclerView, withText("Public Shame"))
        //scrollViewToMatcher(R.id.recyclerView, withText(null))
        scrollViewToMatcher(R.id.recyclerView, withText("Space Program"))
        scrollViewToMatcher(R.id.recyclerView, withText("Meese"))
        scrollViewToMatcher(R.id.recyclerView, withText("Geography"))
        scrollViewToMatcher(R.id.recyclerView, withText("Kittens..."))
        scrollViewToMatcher(R.id.recyclerView, withText("Mounties"))
        scrollViewToMatcher(R.id.recyclerView, withText("Language"))
    }

    @Test
    fun test_swipeRefresh() {
        onViewCheckAssertionWithRetries(withText("About Canada (refresh 0)"), matches(isDisplayed()))

        onView(withId(R.id.swipeContainer)).perform(swipeDown())
        onViewCheckAssertionWithRetries(withText("About Canada (refresh 1)"), matches(isDisplayed()))
        onView(withText("Money")).check(matches(isDisplayed()))

        onView(withId(R.id.swipeContainer)).perform(swipeDown())
        onViewCheckAssertionWithRetries(withText("About Canada (refresh 2)"), matches(isDisplayed()))
        onView(withText("Space Exploration")).check(matches(isDisplayed()))

        onView(withId(R.id.swipeContainer)).perform(swipeDown())
        onViewCheckAssertionWithRetries(withText("About Canada (refresh 3)"), matches(isDisplayed()))
        onView(withText("Celebrities")).check(matches(isDisplayed()))
    }

    @Test
    fun test_serverError() {
        // Note with the current test data the 4th refresh returns no data and an error is expected to be displayed.
        onViewCheckAssertionWithRetries(withText("About Canada (refresh 0)"), matches(isDisplayed()))

        onView(withId(R.id.swipeContainer)).perform(swipeDown())
        onViewCheckAssertionWithRetries(withText("About Canada (refresh 1)"), matches(isDisplayed()))

        onView(withId(R.id.swipeContainer)).perform(swipeDown())
        onViewCheckAssertionWithRetries(withText("About Canada (refresh 2)"), matches(isDisplayed()))

        onView(withId(R.id.swipeContainer)).perform(swipeDown())
        onViewCheckAssertionWithRetries(withText("About Canada (refresh 3)"), matches(isDisplayed()))

        onView(withId(R.id.swipeContainer)).perform(swipeDown())
        onViewCheckAssertionWithRetries(withText("Error"), matches(isDisplayed()))
        onView(withText("No data to display\n\nSwipe down to refresh.")).check(matches(isDisplayed()))

        onView(withId(R.id.swipeContainer)).perform(swipeDown())
        onViewCheckAssertionWithRetries(withText("About Canada (refresh 0)"), matches(isDisplayed()))
    }
}