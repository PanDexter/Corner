package szeptunm.corner.dataaccess.api.pojo

import com.google.gson.annotations.SerializedName


data class Table(
        @SerializedName("position") val position: Int,
        @SerializedName("team") val team: TeamStanding,
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
        @SerializedName("stage") val stage: String,
        @SerializedName("type") val type: String,
        @SerializedName("table") val table: List<Table>
)

data class TeamStanding(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("crestUrl") val crestUrl: String
)






