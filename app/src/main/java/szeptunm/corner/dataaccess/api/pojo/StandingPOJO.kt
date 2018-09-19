package szeptunm.corner.dataaccess.api.pojo

import com.google.gson.annotations.SerializedName


data class Table(
        @SerializedName("position") val position: Int,
        @SerializedName("team") val team: Team,
        @SerializedName("playedGames") val playedGames: Int,
        @SerializedName("won") val won: Int,
        @SerializedName("draw") val draw: Int,
        @SerializedName("lost") val lost: Int,
        @SerializedName("points") val points: Int,
        @SerializedName("goalsFor") val goalsFor: Int,
        @SerializedName("goalsAgainst") val goalsAgainst: Int,
        @SerializedName("goalDifference") val goalDifference: Int
)

data class Standing(
        @SerializedName("competition") val competition: Competition,
        @SerializedName("season") val season: Season,
        @SerializedName("standings") val standings: List<StandingInfo>
)

data class StandingInfo(
        @SerializedName("stage") val stage: String,
        @SerializedName("type") val type: String,
        @SerializedName("table") val table: List<Table>
)






