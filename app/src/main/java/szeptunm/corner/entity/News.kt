package szeptunm.corner.entity

import org.parceler.Parcel
import org.parceler.ParcelConstructor
import szeptunm.corner.dataaccess.database.entity.NewsEntity

@Parcel(Parcel.Serialization.BEAN)
class News : Comparable<News> {

    var id: Int = 0
    var title: String = ""
    var description: String = ""
    var date: String = ""
    var photoUrl: String = ""
    var teamId: Int = 0

    @ParcelConstructor
    constructor(id: Int, title: String, description: String, date: String, photoUrl: String, teamId: Int) {
        this.id = id
        this.title = title
        this.description = description
        this.date = date
        this.photoUrl = photoUrl
        this.teamId = teamId
    }

    constructor(newsEntity: NewsEntity) {
        this.id = newsEntity.id
        this.title = newsEntity.title
        this.description = newsEntity.description
        this.date = newsEntity.date
        this.photoUrl = newsEntity.photoUrl
        this.teamId = newsEntity.teamId
    }

    override fun compareTo(other: News): Int {
        return this.id.compareTo(other.id)
    }
}
