package szeptunm.corner.ui.news

import szeptunm.corner.entity.News
import szeptunm.corner.ui.recycler.AdapterItem

data class NewsItem(val news: News) : AdapterItem {
    override fun getItemType(): Int = 0
}
