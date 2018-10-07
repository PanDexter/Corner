package szeptunm.corner.ui.news

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority.LOW
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import io.reactivex.subjects.PublishSubject
import szeptunm.corner.R
import szeptunm.corner.databinding.NewsItemBinding
import szeptunm.corner.ui.recycler.BindingViewHolder

class NewsViewHolder(binding: NewsItemBinding, val itemObserver: PublishSubject<Int>) :
        BindingViewHolder<NewsItem, NewsItemBinding>(binding) {


    companion object {
        const val KEY_NEWS = "KEY_NEWS"
    }

    lateinit var item: NewsItem

    override fun bind(item: NewsItem) {
        this.item = item
        val context = binding.containerNews.context

        binding.containerNews.setOnClickListener {
            val intentDetail = Intent(context, NewsDetailActivity::class.java)
            val intentWeb = Intent(context, NewsWebActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(KEY_NEWS, item.news)
            intentDetail.putExtras(bundle)
            intentWeb.putExtras(bundle)
            if (item.news.photoUrl != null) {
                context.startActivity(intentDetail)
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

        if (item.news.photoUrl != "") {
            Glide.with(itemView).load(item.news.photoUrl).apply(detailRequest).into(binding.newsPhoto)
        } else {
            Glide.with(itemView).load(item.news.photoUrl).apply(webRequest).into(binding.newsPhoto)
        }
        binding.newsDescription.text = item.news.title
    }
}