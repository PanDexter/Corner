package szeptunm.corner.ui.news

import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority.LOW
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import io.reactivex.subjects.PublishSubject
import szeptunm.corner.R
import szeptunm.corner.databinding.NewsItemBinding
import szeptunm.corner.ui.recycler.BindingViewHolder

class NewsViewHolder(binding: NewsItemBinding, val itemObserver: PublishSubject<Int>, val fragment: NewsFragment) :
        BindingViewHolder<NewsItem, NewsItemBinding>(binding) {


    companion object {
        const val KEY_NEWS = "KEY_NEWS"
    }

    lateinit var item: NewsItem

    override fun bind(item: NewsItem) {
        this.item = item
        val context = binding.containerNews.context
        binding.containerNews.setOnClickListener {
            val intentWeb = Intent(context, NewsWebActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(KEY_NEWS, item.news)
            intentWeb.putExtras(bundle)
            if (item.news.photoUrl != null) {
                prepareTransition()
            } else {
                context.startActivity(intentWeb)
            }
        }
        val webRequest = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .priority(LOW)
                .placeholder(R.drawable.web_placeholder)

        val detailRequest = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .priority(LOW)
                .placeholder(R.drawable.placeholder)

        if (item.news.description != "") {
            Glide.with(itemView).load(item.news.photoUrl).apply(detailRequest).into(binding.newsPhoto)
        } else {
            Glide.with(itemView).load(item.news.photoUrl).apply(webRequest).into(binding.newsPhoto)
        }
        binding.newsDescription.text = item.news.title
    }

    private fun prepareTransition() {
        val intent = Intent(context, NewsDetailActivity::class.java)
        intent.putExtra(KEY_NEWS, item.news)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(fragment.requireActivity(),
                binding.newsPhoto, "newsPhoto")
        context.startActivity(intent, options.toBundle())
    }
}