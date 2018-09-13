package szeptunm.corner.ui.news

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import szeptunm.corner.R
import szeptunm.corner.databinding.NewsDetailsBinding
import szeptunm.corner.entity.News
import szeptunm.corner.ui.ToolbarFragment

class NewsDetailFragment : ToolbarFragment() {
    override val layoutResource: Int
        get() = R.layout.news_details

    private lateinit var binding: NewsDetailsBinding

    companion object {
        fun newInstance(news: News): NewsDetailFragment {
            val fragment = NewsDetailFragment()
            val args = Bundle()
            args.putParcelable("KEY_NEWS", news)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding as NewsDetailsBinding
        val news = arguments?.getParcelable("KEY_NEWS") as News
        binding.newsDetailsDescription.text = news.description
        setImage(news)
    }

    private fun setImage(news: News) {
        val requestOptions = RequestOptions()
                .fitCenter()
        Glide.with(binding.root).load(news.photoUrl).apply(requestOptions).into(binding.newsDetailsPhoto)
    }
}