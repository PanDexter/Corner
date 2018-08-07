package szeptunm.corner.dataaccess.api.pojo

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String
)

data class Area(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String
)