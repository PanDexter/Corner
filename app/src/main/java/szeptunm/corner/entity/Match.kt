package szeptunm.corner.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import szeptunm.corner.dataaccess.database.entity.MatchEntity

@Parcelize
data class Match(
        var homeTeamGoalFull: Int? = null,
        var awayTeamGoalFull: Int? = null,
        var homeTeamGoalExtra: Int? = null,
        var awayTeamGoalExtra: Int? = null,
        var homePenalties: Int? = null,
        var awayPenalties: Int? = null,
        var homeTeam: Int = 0,
        var awayTeam: Int = 0,
        var date: String? = "",
        var competition: Int = 0) : Parcelable {


    constructor(matchEntity: MatchEntity) : this() {
        this.homeTeamGoalFull = matchEntity.homeTeamGoalFull
        this.awayTeamGoalFull = matchEntity.awayTeamGoalFull
        this.homeTeamGoalExtra = matchEntity.homeTeamGoalExtra
        this.awayTeamGoalExtra = matchEntity.awayTeamGoalExtra
        this.homePenalties = matchEntity.homePenalties
        this.awayPenalties = matchEntity.awayPenalties
        this.homeTeam = matchEntity.homeTeam
        this.awayTeam = matchEntity.awayTeam
        this.date = matchEntity.matchDate
        this.competition = matchEntity.competition
    }
}