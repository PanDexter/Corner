package szeptunm.corner.dataaccess.api.pojo

import com.google.gson.annotations.SerializedName

data class Player(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("position") val position: Any?,
        @SerializedName("dateOfBirth") val dateOfBirth: String,
        @SerializedName("countryOfBirth") val countryOfBirth: String,
        @SerializedName("nationality") val nationality: String,
        @SerializedName("role") val role: String
)
