package szeptunm.corner.dataaccess.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "player",
        foreignKeys = [
            (ForeignKey(entity = TeamEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["teamId"],
                    onDelete = CASCADE))
        ])
data class PlayerEntity constructor(
        @ColumnInfo(name = "name")
        var name: String = "",
        @ColumnInfo(name = "position")
        var position: String = "",
        @ColumnInfo(name = "dateOfBirth")
        var dateOfBirth: String = "",
        @ColumnInfo(name = "nationality")
        var nationality: String = "",
        @ColumnInfo(name = "teamId")
        var teamId: Int? = 0,
        @ColumnInfo(name = "description")
        var description: String = "",
        @ColumnInfo(name = "thumbUrl")
        var thumbUrl: String = "",
        @ColumnInfo(name = "cutOutUrl")
        var cutOutUrl: String? = "",
        @ColumnInfo(name = "weight")
        var weight: String = "",
        @ColumnInfo(name = "height")
        var height: String = ""
) {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
