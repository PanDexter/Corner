package szeptunm.corner.domain.splashScreen

import szeptunm.corner.commons.Preferences
import javax.inject.Inject

class IsFirstInitialized @Inject constructor(private val preferences: Preferences) {

    companion object {
        private const val IS_FIRST_TIME = "IS_FIRST_TIME"
    }

    val isInitialized
        get() = preferences.getPreferenceBoolean(IS_FIRST_TIME, false)

    fun execute(): Boolean = (!isInitialized)
            .also { preferences.putPreferenceBoolean(IS_FIRST_TIME, true) }
}