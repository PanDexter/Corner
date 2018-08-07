package szeptunm.corner.dataaccess.api.model

import com.google.gson.annotations.SerializedName
import szeptunm.corner.dataaccess.api.pojo.Item

data class NewsResponse(
        @SerializedName("status") val status: String,
        @SerializedName("items") val items: List<Item>
)