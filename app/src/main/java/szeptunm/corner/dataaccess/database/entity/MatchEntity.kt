package szeptunm.corner.dataaccess.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "match",
        foreignKeys = [
            (ForeignKey(entity = TeamEntity::class,
                    parentColumns = ["name"],
                    childColumns = ["homeTeam"],
                    onDelete = ForeignKey.CASCADE)),
            (ForeignKey(entity = TeamEntity::class,
                    parentColumns = ["name"],
                    childColumns = ["awayTeam"],
                    onDelete = ForeignKey.CASCADE)),
            (ForeignKey(entity = CompetitionEntity::class,
                    parentColumns = ["name"],
                    childColumns = ["competition"],
                    onDelete = ForeignKey.CASCADE))
        ],
        primaryKeys = ["homeTeam", "awayTeam", "date"],
        indices = [(Index(value = ["homeTeam", "awayTeam"], unique = true))])
class MatchEntity constructor(
    @ColumnInfo(name = "homeTeamGoalFull")
    var homeTeamGoalFull: Int? = null,
    @ColumnInfo(name = "awayTeamGoalFull")
    var awayTeamGoalFull: Int? = null,
    @ColumnInfo(name = "homeTeamExtra")
    var homeTeamGoalExtra: Int? = null,
    @ColumnInfo(name = "awayTeamExtra")
    var awayTeamGoalExtra: Int? = null,
    @ColumnInfo(name = "homePenalties")
    var homePenalties: Int? = null,
    @ColumnInfo(name = "awayPenalties")
    var awayPenalties: Int? = null,
        @ColumnInfo(name = "homeTeam")
        var homeTeam: String = "",
        @ColumnInfo(name = "awayTeam")
        var awayTeam: String = "",
        @ColumnInfo(name = "matchDate")
        var date: String? = "",
    @ColumnInfo(name = "competitionId")
    var competition: String? = ""
)