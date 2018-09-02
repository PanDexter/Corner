package szeptunm.corner.dataaccess.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "match",
        foreignKeys = [
            (ForeignKey(entity = TeamEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["homeTeam"],
                    onDelete = ForeignKey.CASCADE)),
            (ForeignKey(entity = TeamEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["awayTeam"],
                    onDelete = ForeignKey.CASCADE)),
            (ForeignKey(entity = CompetitionEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["competitionId"],
                    onDelete = ForeignKey.CASCADE))
        ],
        primaryKeys = ["homeTeam", "awayTeam", "matchDate"],
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
        var homeTeam: Int = 0,
        @ColumnInfo(name = "awayTeam")
        var awayTeam: Int = 0,
        @ColumnInfo(name = "matchDate")
        var matchDate: String = "",
        @ColumnInfo(name = "competitionId")
        var competition: Int = 0
)