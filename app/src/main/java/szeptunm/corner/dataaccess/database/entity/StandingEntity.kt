package szeptunm.corner.dataaccess.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "standing",
        foreignKeys = [
            (ForeignKey(entity = TeamEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["teamId"],
                    onDelete = ForeignKey.CASCADE)),
            (ForeignKey(entity = CompetitionEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["competitionId"],
                    onDelete = ForeignKey.CASCADE))
        ],
        primaryKeys = ["teamId", "currentMatchDay", "competitionId"])
data class StandingEntity(
        @ColumnInfo(name = "currentMatchDay")
        var currentMatchDay: Int = 0,
        @ColumnInfo(name = "position")
        var position: Int = 0,
        @ColumnInfo(name = "playedGames")
        var playedGames: Int = 0,
        @ColumnInfo(name = "won")
        var won: Int = 0,
        @ColumnInfo(name = "draw")
        var draw: Int = 0,
        @ColumnInfo(name = "lost")
        var lost: Int = 0,
        @ColumnInfo(name = "points")
        var points: Int = 0,
        @ColumnInfo(name = "goalsDifference")
        var goalsDifference: Int = 0,
        @ColumnInfo(name = "teamId")
        var teamId: Int = 0,
        @ColumnInfo(name = "competitionId")
        var competitionId: Int = 0
)