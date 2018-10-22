package szeptunm.corner.domain.splashScreen

import szeptunm.corner.commons.Constants.KEY_CLUB_FAVOURITE
import szeptunm.corner.commons.Preferences
import javax.inject.Inject

class GetFavouriteTeam @Inject constructor() {

    @Inject
    lateinit var preferences: Preferences

    companion object {
        const val DEFAULT_NAME: String = "FC Barcelona"
    }

    fun getClubName(): String = preferences.getPreferenceString(KEY_CLUB_FAVOURITE, DEFAULT_NAME)!!
}