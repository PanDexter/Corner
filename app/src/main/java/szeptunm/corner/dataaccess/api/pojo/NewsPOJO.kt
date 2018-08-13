package szeptunm.corner.dataaccess.api.pojo

import com.google.gson.annotations.SerializedName

data class Item(
        @SerializedName("title") var title: String,
        @SerializedName("pubDate") var pubDate: String,
        @SerializedName("link") var link: String,
        @SerializedName("description") var description: String,
        @SerializedName("enclosure") var enclosure: Enclosure
)

data class Enclosure(@SerializedName("link") var imageURL: String)