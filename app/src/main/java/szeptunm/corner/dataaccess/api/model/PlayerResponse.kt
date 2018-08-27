package szeptunm.corner.dataaccess.api.model

import com.google.gson.annotations.SerializedName
import szeptunm.corner.dataaccess.api.pojo.Player

data class PlayerResponse(
        @SerializedName("player") val squad: List<Player>
)
