package szeptunm.corner.dataaccess.api.model

import com.google.gson.annotations.SerializedName
import szeptunm.corner.dataaccess.api.pojo.Match

data class MatchResponse(
        @SerializedName("matches") val matches: List<Match>
)