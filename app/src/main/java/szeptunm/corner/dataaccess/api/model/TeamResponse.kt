package szeptunm.corner.dataaccess.api.model

import com.google.gson.annotations.SerializedName
import szeptunm.corner.dataaccess.api.pojo.Area
import szeptunm.corner.dataaccess.api.pojo.Player

data class TeamResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("area") val area: Area,
        @SerializedName("name") val name: String,
        @SerializedName("shortName") val shortName: String,
        @SerializedName("tla") val tla: String,
        @SerializedName("founded") val founded: Int,
        @SerializedName("clubColors") val clubColors: String,
        @SerializedName("squad") val squad: List<Player>
)
