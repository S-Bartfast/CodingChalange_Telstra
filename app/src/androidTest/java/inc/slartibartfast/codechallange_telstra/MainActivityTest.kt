package inc.slartibartfast.codechallange_telstra

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
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
        Thread.sleep(3000)
        onView(withText("About Canada (refresh 0)")).check(matches(isDisplayed()))
    }
}