package szeptunm.corner.dataaccess.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "standing")
class Standing {
    @ColumnInfo(name = "id")@PrimaryKey var id:Int = 0
}