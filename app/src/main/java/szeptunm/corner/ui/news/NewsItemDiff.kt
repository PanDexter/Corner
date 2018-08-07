package szeptunm.corner.ui.news

import androidx.recyclerview.widget.DiffUtil

class NewsItemDiff(private val oldItems: List<NewsItem>, private val newItems: List<NewsItem>) :
        DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return getNewsTitle(oldItems, oldPosition) == getNewsTitle(newItems, newPosition)
    }

    private fun getNewsTitle(items: List<NewsItem>, position: Int): String {
        return items[position].news.title
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldItems[oldPosition].news == newItems[newPosition].news
    }
}