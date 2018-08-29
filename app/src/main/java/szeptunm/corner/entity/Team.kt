package szeptunm.corner.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import szeptunm.corner.dataaccess.database.entity.TeamEntity

@Parcelize
data class Team(
        var id: Int = 0,
        var name: String = "") : Parcelable {

    constructor(teamEntity: TeamEntity) : this() {
        this.id = teamEntity.id
        this.name = teamEntity.name
    }
}
