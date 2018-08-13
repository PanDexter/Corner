package szeptunm.corner.dataaccess.api.model

import com.google.gson.annotations.SerializedName
import szeptunm.corner.dataaccess.api.pojo.Item

data class NewsResponse(
        @SerializedName("status") var status: String,
        @SerializedName("items") var items: List<Item>
)