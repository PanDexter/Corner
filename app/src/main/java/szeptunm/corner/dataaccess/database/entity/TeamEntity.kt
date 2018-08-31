package szeptunm.corner.dataaccess.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "team")
class TeamEntity constructor(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0,
        @ColumnInfo(name = "name")
        var name: String = ""
)