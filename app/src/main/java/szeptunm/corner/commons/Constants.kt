package szeptunm.corner.commons

import java.text.SimpleDateFormat
import java.util.Locale

object Constants {
    const val KEY_CLUB_NAME = "KEY_CLUB_NAME" // ClubInfo name choosed currently by user in app flow
    const val KEY_CLUB_FAVOURITE = "KEY_CLUB_INFO"
    const val KEY_CLUB_ID = "KEY_CLUB_ID" // ClubInfo Id used in Schedule viewholder
    const val IS_DURING_FLOW = "IS DURING FLOW" // Check if we are during flow or not
    val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
}