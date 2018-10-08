package szeptunm.corner.ui.news

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.team_banner.view.team_name
import kotlinx.android.synthetic.main.team_banner.view.team_photo
import szeptunm.corner.R
import szeptunm.corner.databinding.FragmentNewsBinding
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.ui.BaseFragment
import javax.inject.Inject

class NewsFragment : BaseFragment() {


    @Inject
    lateinit var newsAdapter: NewsAdapter

    @Inject
    lateinit var viewModel: NewsViewModel

    @Inject
    lateinit var clubInfo: ClubInfo

    private lateinit var binding: FragmentNewsBinding


    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        fun newInstance(): NewsFragment = NewsFragment()
    }

    override val layoutResource: Int
        get() = R.layout.fragment_news

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = viewDataBinding as FragmentNewsBinding
        binding.banner.team_name.text = clubInfo.name
        binding.banner.background = ContextCompat.getDrawable(requireContext(), clubInfo.gradient)
        binding.banner.team_photo.setImageDrawable(ContextCompat.getDrawable(requireContext(), clubInfo.badge))
        subscribeToViewModel()
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