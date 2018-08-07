package szeptunm.corner.dataaccess.api.pojo

import com.google.gson.annotations.SerializedName

data class Item(
        @SerializedName("title") val title: String,
        @SerializedName("pubDate") val pubDate: String,
        @SerializedName("link") val link: String,
        @SerializedName("description") val description: String,
        @SerializedName("enclosure") val enclosure: Enclosure
)

data class Enclosure(@SerializedName("link") val imageURL: String)