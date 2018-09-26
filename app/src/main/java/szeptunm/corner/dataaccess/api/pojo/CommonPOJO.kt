package szeptunm.corner.dataaccess.api.pojo

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("crestUrl") val crestUrl: String?
)

data class Area(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String
)

data class Season(
        @SerializedName("id") val id: Int,
        @SerializedName("startDate") val startDate: String,
        @SerializedName("endDate") val endDate: String,
        @SerializedName("currentMatchday") val currentMatchday: Int
)