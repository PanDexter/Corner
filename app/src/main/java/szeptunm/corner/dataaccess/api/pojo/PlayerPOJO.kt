package szeptunm.corner.dataaccess.api.pojo

import com.google.gson.annotations.SerializedName

data class Player(
        @SerializedName("idPlayer") val id: Int,
        @SerializedName("idTeam") val idTeam: Int,
        @SerializedName("strPlayer") val name: String,
        @SerializedName("dateBorn") val dateOfBirth: String,
        @SerializedName("strDescriptionEN") val description: String,
        @SerializedName("strNationality") val nationality: String,
        @SerializedName("strPosition") val position: String,
        @SerializedName("strThumb") val thumbUrl: String,
        @SerializedName("strCutout") val cutOutUrl: String
)
