package szeptunm.corner.dataaccess.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clubInfo")
data class ClubInfoEntity(
        @ColumnInfo(name = "name")
        @PrimaryKey
        var name: String,
        @ColumnInfo(name = "newsUrl")
        var newsUrl: String,
        @ColumnInfo(name = "teamApiId")
        var teamApiId: Int,
        @ColumnInfo(name = "matchTeamId")
        var matchTeamId: Int,
        @ColumnInfo(name = "competitionId")
        var competitionId: Int,
        @ColumnInfo(name = "badge")
        var badge: Int,
        @ColumnInfo(name = "gradient")
        var gradient: Int
)