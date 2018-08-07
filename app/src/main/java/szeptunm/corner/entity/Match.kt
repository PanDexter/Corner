package szeptunm.corner.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class Match {

    var id: Int = 0
    var homeTeamGoalFull: Int? = null
    var awayTeamGoalFull: Int? = null
    var homeTeamGoalExtra: Int? = null
    var awayTeamGoalExtra: Int? = null
    var homePenalties: Int? = null
    var awayPenalties: Int? = null
    var homeTeam: Team? = null
    var awayTeam: Team? = null
    var competitionId: Competition? = null
}