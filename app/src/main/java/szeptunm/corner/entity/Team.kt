package szeptunm.corner.entity

import org.parceler.Parcel
import org.parceler.ParcelConstructor
import szeptunm.corner.dataaccess.database.entity.TeamEntity

@Parcel(Parcel.Serialization.BEAN)
class Team : Comparable<Team> {

    var id: Int = 0

    var name: String = ""
    var shortName: String = ""
    var tla: String = ""

    @ParcelConstructor
    constructor(id: Int, name: String, shortName: String, tla: String) {
        this.id = id
        this.name = name
        this.shortName = shortName
        this.tla = tla
    }

    constructor(teamEntity: TeamEntity) {
        this.id = teamEntity.id
        this.name = teamEntity.name
        this.shortName = teamEntity.shortname
        this.tla = teamEntity.shortname
    }

    override fun compareTo(other: Team): Int = this.name.compareTo(other.name)

}
