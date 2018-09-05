package szeptunm.corner.ui.news

import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority.LOW
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import io.reactivex.subjects.PublishSubject
import szeptunm.corner.R
import szeptunm.corner.databinding.NewsItemBinding
import szeptunm.corner.ui.FragmentChanger
import szeptunm.corner.ui.MainActivity
import szeptunm.corner.ui.recycler.BindingViewHolder

class NewsViewHolder(binding: NewsItemBinding, val itemObserver: PublishSubject<Int>) :
        BindingViewHolder<NewsItem, NewsItemBinding>(binding) {


    lateinit var item: NewsItem

    override fun bind(item: NewsItem) {
        this.item = item
        val fragmentChanger = FragmentChanger()

        val activity = context as MainActivity
        binding.containerNews.setOnClickListener {
            fragmentChanger.changeFragments(activity, R.id.fragment_placeholder,
                    "newsDetail") { chooseFragment(item) }
        }
        val requestOptions = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .priority(LOW)
                .placeholder(R.drawable.barcelonabadge)

        Glide.with(itemView).load(item.news.photoUrl).apply(requestOptions).into(binding.newsPhoto)
        binding.newsDescription.text = item.news.title
    }

    private fun chooseFragment(item: NewsItem): Fragment =
            if (item.news.photoUrl != null) {
                NewsDetailFragment.newInstance(item.news)
            } else {
                NewsWebFragment.newInstance(item.news)
            }
}