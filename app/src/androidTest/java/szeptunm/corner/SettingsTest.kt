package szeptunm.corner

import androidx.test.espresso.Espresso.onView
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

    @Before
    fun init() {
        activity.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun testSettings() {
        onView(withId(R.id.navigation)).perform()
    }
}