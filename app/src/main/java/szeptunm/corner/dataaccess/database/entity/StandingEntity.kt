package szeptunm.corner.dataaccess.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "standing")
class StandingEntity {

    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int = 0
    @ColumnInfo(name = "currentMatchDay")
    var currentMatchDay: Int = 0
}