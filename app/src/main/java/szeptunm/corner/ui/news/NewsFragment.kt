package szeptunm.corner.ui.news

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import szeptunm.corner.R
import szeptunm.corner.databinding.FragmentNewsBinding
import szeptunm.corner.ui.BaseFragment
import javax.inject.Inject

class NewsFragment : BaseFragment() {

    @Inject
    lateinit var newsAdapter: NewsAdapter

    @Inject
    lateinit var viewModel: NewsViewModel

    private lateinit var binding: FragmentNewsBinding

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {

        fun newInstance(): NewsFragment = NewsFragment()
    }

    override val layoutResource: Int
        get() = R.layout.fragment_news

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = viewDataBinding as FragmentNewsBinding
        setupRecycle()
        subscribeToViewModel()
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