package szeptunm.corner.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchSchedule(
        var homeTeamGoalFull: Int? = null,
        var awayTeamGoalFull: Int? = null,
        var homeTeamGoalExtra: Int? = null,
        var awayTeamGoalExtra: Int? = null,
        var homePenalties: Int? = null,
        var awayPenalties: Int? = null,
        var homeTeam: String = "",
        var awayTeam: String = "",
        var date: String? = "",
        var competition: String = "") : Parcelable