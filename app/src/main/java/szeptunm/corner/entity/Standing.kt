package szeptunm.corner.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import szeptunm.corner.dataaccess.database.entity.StandingEntity

@Parcelize
data class Standing(
        var currentMatchDay: Int = 0,
        var position: Int? = 0,
        var playedGames: Int? = 0,
        var won: Int? = 0,
        var draw: Int? = 0,
        var lost: Int? = 0,
        var points: Int? = 0,
        var goalsFor: Int? = 0,
        var goalAgainst: Int? = 0,
        var teamId: Int = 0,
        var competitionId: Int = 0) : Parcelable {

    constructor(standingEntity: StandingEntity) : this() {
        this.currentMatchDay = standingEntity.currentMatchDay
        this.position = standingEntity.position
        this.playedGames = standingEntity.playedGames
        this.won = standingEntity.won
        this.draw = standingEntity.draw
        this.lost = standingEntity.lost
        this.points = standingEntity.points
        this.goalsFor = standingEntity.goalsFor
        this.goalAgainst = standingEntity.goalAgainst
        this.teamId = standingEntity.teamId
        this.competitionId = standingEntity.competitionId
    }
}