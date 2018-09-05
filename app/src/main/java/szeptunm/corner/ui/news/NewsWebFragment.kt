package szeptunm.corner.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dagger.android.support.DaggerFragment
import szeptunm.corner.R
import szeptunm.corner.databinding.NewsWebBinding
import szeptunm.corner.entity.News

class NewsWebFragment : DaggerFragment() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.news_web, container, false)
        val news = arguments?.getParcelable("KEY_NEWS_WEB") as News
        binding.webView.loadUrl(news.link)
        binding.toolbar.navigationIcon = resources.getDrawable(R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        return binding.root
    }
}