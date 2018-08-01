package szeptunm.corner.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
class Team {

    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int = 0
    @ColumnInfo(name = "name")
    var name: String = ""
    @ColumnInfo(name = "shortname")
    var shortname: String = ""
    @ColumnInfo(name = "tla")
    var tla: String = ""
}