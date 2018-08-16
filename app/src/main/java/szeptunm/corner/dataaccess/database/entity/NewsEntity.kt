package szeptunm.corner.dataaccess.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "news",
        foreignKeys = [
            (ForeignKey(entity = TeamEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["teamId"],
                    onDelete = ForeignKey.CASCADE))
        ])
data class NewsEntity constructor(
        @ColumnInfo(name = "title")
        var title: String = "",
        @ColumnInfo(name = "description")
        var description: String = "",
        @ColumnInfo(name = "date")
        var date: String = "",
        @ColumnInfo(name = "photoUrl")
        var photoUrl: String? = "",
        @ColumnInfo(name = "link")
        var link: String? = "",
        @ColumnInfo(name = "teamId")
        var teamId: Int? = 0) {

        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
}