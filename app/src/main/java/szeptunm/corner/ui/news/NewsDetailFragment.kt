package szeptunm.corner.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.DaggerFragment
import szeptunm.corner.R
import szeptunm.corner.databinding.NewsDetailsBinding
import szeptunm.corner.entity.News

class NewsDetailFragment : DaggerFragment() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.news_details, container, false)
        val news = arguments?.getParcelable("KEY_NEWS") as News
        binding.newsDetailsDescription.text = news.description
        setImage(news)
        binding.toolbar.navigationIcon = resources.getDrawable(R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        return binding.root
    }

    private fun setImage(news: News) {
        val requestOptions = RequestOptions()
                .fitCenter()
        Glide.with(binding.root).load(news.photoUrl).apply(requestOptions).into(binding.newsDetailsPhoto)
    }

    override fun onDetach() {
        super.onDetach()
    }
}