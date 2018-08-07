package szeptunm.corner.dataaccess.api.model

import com.google.gson.annotations.SerializedName
import szeptunm.corner.dataaccess.api.pojo.Season
import szeptunm.corner.dataaccess.api.pojo.Standing

data class StandingResponse(
        @SerializedName("season") val season: Season,
        @SerializedName("standings") val standings: List<Standing>
)