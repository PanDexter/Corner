package szeptunm.corner.dataaccess.api.model

import com.google.gson.annotations.SerializedName
import szeptunm.corner.dataaccess.api.pojo.Competition
import szeptunm.corner.dataaccess.api.pojo.Season
import szeptunm.corner.dataaccess.api.pojo.StandingInfo

data class StandingResponse(
        @SerializedName("competition") val competition: Competition,
        @SerializedName("season") val season: Season,
        @SerializedName("standings") val standings: List<StandingInfo>
)