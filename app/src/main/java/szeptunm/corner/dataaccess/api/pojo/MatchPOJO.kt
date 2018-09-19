package szeptunm.corner.dataaccess.api.pojo

import com.google.gson.annotations.SerializedName

data class Competition(
        @SerializedName("id") val id: Int,
        @SerializedName("area") val area: Area,
        @SerializedName("name") val name: String,
        @SerializedName("code") val code: Any?,
        @SerializedName("plan") val plan: String
)


data class Result(
        @SerializedName("fullTime") val fullTime: Score,
        @SerializedName("extraTime") val extraTime: Score,
        @SerializedName("penalties") val penalties: Score
)

data class Score(
        @SerializedName("homeTeam") val homeTeam: Int?,
        @SerializedName("awayTeam") val awayTeam: Int?
)

data class Match(
        @SerializedName("id") val id: Int,
        @SerializedName("competition") val competition: Competition,
        @SerializedName("season") val season: Season,
        @SerializedName("utcDate") val utcDate: String,
        @SerializedName("status") val status: String,
        @SerializedName("matchday") val matchDay: Int,
        @SerializedName("group") val group: String,
        @SerializedName("homeTeam") val homeTeam: Team,
        @SerializedName("awayTeam") val awayTeam: Team,
        @SerializedName("score") val score: Result
)