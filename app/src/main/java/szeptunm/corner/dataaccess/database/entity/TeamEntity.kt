package szeptunm.corner.dataaccess.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(tableName = "team", primaryKeys = ["id", "name"], indices = [Index(value = ["id", "name"], unique = true)])
class TeamEntity constructor(
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String = ""
)