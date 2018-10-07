package szeptunm.corner.ui.news

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.team_banner.view.team_name
import szeptunm.corner.R
import szeptunm.corner.databinding.FragmentNewsBinding
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.ui.BaseFragment
import szeptunm.corner.ui.splashScreen.SplashScreenActivity.Companion.KEY_CLUB_INFO
import javax.inject.Inject

class NewsFragment : BaseFragment() {


    @Inject
    lateinit var newsAdapter: NewsAdapter

    @Inject
    lateinit var viewModel: NewsViewModel

    private lateinit var binding: FragmentNewsBinding

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        fun newInstance(clubInfo: ClubInfo): NewsFragment =
                NewsFragment().apply {
                    arguments = bundleOf(KEY_CLUB_INFO to clubInfo)
                }
    }

    override val layoutResource: Int
        get() = R.layout.fragment_news

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val clubInfo = arguments?.getParcelable(KEY_CLUB_INFO) as ClubInfo
        binding = viewDataBinding as FragmentNewsBinding
        binding.banner.team_name.text = clubInfo.name
        subscribeToViewModel()
        viewModel.init(clubInfo)
        setupRecycle()
    }

    private fun setupRecycle() {
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun subscribeToViewModel() {
        viewModel.observeNews().subscribe {
            newsAdapter.setDataWithDiff(it)
        }.addTo(compositeDisposable)
    }
}