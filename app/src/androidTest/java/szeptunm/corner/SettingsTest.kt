package szeptunm.corner

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.junit.*
import org.junit.runner.*
import szeptunm.corner.ui.MainActivity

@RunWith(AndroidJUnit4ClassRunner::class)
class SettingsTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testSettings() {
        //Open news
        onView(withId(R.id.navigation_news)).perform(click())

        //Click one of items

        onView(withId(R.id.rv_news)).check(matches(isClickable()))

        //Open team



    }
}