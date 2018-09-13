package szeptunm.corner.ui.news

import android.os.Bundle
import android.view.View
import szeptunm.corner.R
import szeptunm.corner.databinding.NewsWebBinding
import szeptunm.corner.entity.News
import szeptunm.corner.ui.ToolbarFragment

class NewsWebFragment : ToolbarFragment() {


    override val layoutResource: Int
        get() = R.layout.news_web

    private lateinit var binding: NewsWebBinding

    companion object {
        fun newInstance(news: News): NewsWebFragment {
            val fragment = NewsWebFragment()
            val args = Bundle()
            args.putParcelable("KEY_NEWS_WEB", news)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding as NewsWebBinding
        val news = arguments?.getParcelable("KEY_NEWS_WEB") as News
        binding.webView.loadUrl(news.link)
    }
}