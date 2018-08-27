package szeptunm.corner.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import szeptunm.corner.dataaccess.database.entity.PlayerEntity

@Parcelize
data class Player(
        var name: String = "",
        var position: String = "",
        var dateOfBirth: String = "",
        var nationality: String = "",
        var teamId: Int? = 0,
        var description: String = "",
        var thumbUrl: String = "",
        var cutOutUrl: String? = "") : Parcelable {

    constructor(playerEntity: PlayerEntity) : this() {
        this.name = playerEntity.name
        this.position = playerEntity.position
        this.dateOfBirth = playerEntity.dateOfBirth
        this.nationality = playerEntity.nationality
        this.teamId = playerEntity.teamId
        this.description = playerEntity.description
        this.thumbUrl = playerEntity.thumbUrl
        this.cutOutUrl = playerEntity.cutOutUrl
    }
}