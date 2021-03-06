package szeptunm.corner.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.PublishSubject
import szeptunm.corner.databinding.NewsItemBinding
import szeptunm.corner.ui.recycler.BindingViewHolder
import javax.inject.Inject

class NewsAdapter @Inject constructor(val fragment: NewsFragment) :
        RecyclerView.Adapter<BindingViewHolder<NewsItem, NewsItemBinding>>() {

    var items: List<NewsItem> = ArrayList()
    private var onItemSelected: PublishSubject<Int> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<NewsItem, NewsItemBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding, onItemSelected, fragment)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BindingViewHolder<NewsItem, NewsItemBinding>, position: Int) {
        holder.bind(items[holder.adapterPosition])
    }

    fun setDataWithDiff(newList: List<NewsItem>) {
        this.items = newList
        notifyDataSetChanged()
    }
}