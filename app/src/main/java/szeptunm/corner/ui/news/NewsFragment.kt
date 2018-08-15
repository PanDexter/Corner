package szeptunm.corner.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import szeptunm.corner.R
import szeptunm.corner.databinding.FragmentNewsBinding
import javax.inject.Inject

class NewsFragment : DaggerFragment() {

    @Inject
    lateinit var newsAdapter: NewsAdapter

    @Inject
    lateinit var viewModel: NewsViewModel

    private lateinit var binding: FragmentNewsBinding
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        fun newInstance(): NewsFragment = NewsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        setupRecycle()
        subscribeToViewModel()
        return binding.root
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