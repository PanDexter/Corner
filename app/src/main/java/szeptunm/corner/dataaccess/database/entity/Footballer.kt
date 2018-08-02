package szeptunm.corner.dataaccess.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "footbaler",
        foreignKeys = [
            (ForeignKey(entity = Team::class,
                    parentColumns = ["id"],
                    childColumns = ["teamId"],
                    onDelete = CASCADE))
        ])
class Footballer {

    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int = 0
    @ColumnInfo(name = "name")
    var name: String = ""
    @ColumnInfo(name = "position")
    var position: String = ""
    @ColumnInfo(name = "dateOfBirth")
    var dateOfBirth: String = ""
    @ColumnInfo(name = "nationality")
    var nationality: String = ""
    @ColumnInfo(name = "role")
    var role: String = ""
    @ColumnInfo(name = "club")
    var teamId: Int = 0
}