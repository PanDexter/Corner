package szeptunm.corner.ui.news

import io.reactivex.subjects.PublishSubject
import szeptunm.corner.databinding.NewsItemBinding
import szeptunm.corner.ui.recycler.BindingViewHolder

class NewsViewHolder(binding: NewsItemBinding, val itemObserver: PublishSubject<Int>) :
        BindingViewHolder<NewsItem, NewsItemBinding>(binding) {

    lateinit var item: NewsItem

    override fun bind(item: NewsItem) {
        this.item = item
        binding.news = item.news
    }
}