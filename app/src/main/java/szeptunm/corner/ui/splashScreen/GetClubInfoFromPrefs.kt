package szeptunm.corner.ui.splashScreen

import szeptunm.corner.commons.Constants
import szeptunm.corner.commons.Preferences
import javax.inject.Inject

class GetClubInfoFromPrefs @Inject constructor() {

    @Inject
    lateinit var preferences: Preferences

    companion object {
        const val DEFAULT_NAME = "FC Barcelona"
    }

    fun getClubName(): String {
        val name = preferences.getPreferenceString(Constants.KEY_CLUB_NAME, "")
        return when (name) {
            null -> DEFAULT_NAME
            else -> name
        }
    }
}