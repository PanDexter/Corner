package szeptunm.corner.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import szeptunm.corner.dataaccess.database.entity.CompetitionEntity

@Parcelize
data class Competition(
        var id: Int = 0,
        var name: String = "") : Parcelable {

    constructor(competitionEntity: CompetitionEntity) : this() {
        this.id = competitionEntity.id
        this.name = competitionEntity.name
    }
}