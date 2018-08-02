package szeptunm.corner.dataaccess.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "match",
        foreignKeys = [
            (ForeignKey(entity = Team::class,
                    parentColumns = ["id"],
                    childColumns = ["homeTeamId"],
                    onDelete = ForeignKey.CASCADE)),
            (ForeignKey(entity = Team::class,
                    parentColumns = ["id"],
                    childColumns = ["awayTeamId"],
                    onDelete = ForeignKey.CASCADE)),
            (ForeignKey(entity = Competition::class,
                    parentColumns = ["id"],
                    childColumns = ["competitionId"],
                    onDelete = ForeignKey.CASCADE))
        ])
class Match {

    @ColumnInfo(name = "id")
    var id: Int = 0
    @ColumnInfo(name = "homeTeamGoalFull")
    var homeTeamGoalFull: Int? = null
    @ColumnInfo(name = "awayTeamGoalFull")
    var awayTeamGoalFull: Int? = null
    @ColumnInfo(name = "homeTeamExtra")
    var homeTeamGoalExtra: Int? = null
    @ColumnInfo(name = "awayTeamExtra")
    var awayTeamGoalExtra: Int? = null
    @ColumnInfo(name = "homePenalties")
    var homePenalties: Int? = null
    @ColumnInfo(name = "awayPenalties")
    var awayPenalties: Int? = null
    @ColumnInfo(name = "homeTeamId")
    var homeTeamId: Int = 0
    @ColumnInfo(name = "awayTeamId")
    var awayTeamId: Int = 0
    @ColumnInfo(name = "competitionId")
    var competitionId: Int = 0
}