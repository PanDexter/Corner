package szeptunm.corner.ui.settings

import szeptunm.corner.commons.Constants.KEY_CLUB_FAVOURITE
import szeptunm.corner.commons.Constants.KEY_CLUB_NAME
import szeptunm.corner.commons.Preferences
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private val preferences: Preferences) {

    fun setNewClubName(clubName: String) {
        preferences.putPreferenceString(KEY_CLUB_NAME, clubName)
    }

    fun setFavouriteClubName(clubName: String) {
        preferences.putPreferenceString(KEY_CLUB_FAVOURITE, clubName)
    }
}