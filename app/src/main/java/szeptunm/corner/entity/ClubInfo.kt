package szeptunm.corner.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import szeptunm.corner.dataaccess.database.entity.ClubInfoEntity

@Parcelize
data class ClubInfo constructor(
        var name: String = "",
        var newsUrl: String = "",
        var teamApiId: Int = 0,
        var matchTeamId: Int = 0,
        var competitionId: Int = 0,
        var gradient: Int = 0,
        var badge: Int = 0) : Parcelable {


    constructor(clubInfoEntity: ClubInfoEntity) : this() {
        this.name = clubInfoEntity.name
        this.newsUrl = clubInfoEntity.newsUrl
        this.teamApiId = clubInfoEntity.teamApiId
        this.matchTeamId = clubInfoEntity.matchTeamId
        this.competitionId = clubInfoEntity.competitionId
        this.badge = clubInfoEntity.badge
        this.gradient = clubInfoEntity.gradient
    }
}