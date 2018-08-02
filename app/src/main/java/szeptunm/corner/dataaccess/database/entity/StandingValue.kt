package szeptunm.corner.dataaccess.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "standingValues",
        foreignKeys = [
            (ForeignKey(entity = Team::class,
                    parentColumns = ["id"],
                    childColumns = ["teamId"],
                    onDelete = ForeignKey.CASCADE)),
            (ForeignKey(entity = Standing::class,
                    parentColumns = ["id"],
                    childColumns = ["standingId"],
                    onDelete = ForeignKey.CASCADE)),
            (ForeignKey(entity = Competition::class,
                    parentColumns = ["id"],
                    childColumns = ["competitionId"],
                    onDelete = ForeignKey.CASCADE))
        ])
class StandingValue {

    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int = 0
    @ColumnInfo(name = "position")
    var position: Int = 0
    @ColumnInfo(name = "playedGames")
    var playedGames: Int = 0
    @ColumnInfo(name = "won")
    var won: Int = 0
    @ColumnInfo(name = "draw")
    var draw: Int = 0
    @ColumnInfo(name = "lost")
    var lost: Int = 0
    @ColumnInfo(name = "points")
    var points: Int = 0
    @ColumnInfo(name = "goalsFor")
    var goalsFor: Int = 0
    @ColumnInfo(name = "goalsAgainst")
    var goalAgainst: Int = 0
    @ColumnInfo(name = "teamId")
    var teamId: Int = 0
    @ColumnInfo(name = "standingId")
    var standingId: Int = 0
    @ColumnInfo(name = "competitionId")
    var competitionId: Int = 0
}