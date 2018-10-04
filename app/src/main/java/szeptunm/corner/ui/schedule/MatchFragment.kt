package szeptunm.corner.ui.schedule

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import szeptunm.corner.R
import szeptunm.corner.databinding.FragmentScheduleBinding
import szeptunm.corner.ui.BaseFragment
import javax.inject.Inject

class MatchFragment : BaseFragment() {
    override val layoutResource: Int
        get() = R.layout.fragment_schedule

    @Inject
    lateinit var matchAdapter: MatchAdapter

    @Inject
    lateinit var viewModel: MatchViewModel

    private lateinit var binding: FragmentScheduleBinding
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        fun newInstance(): MatchFragment = MatchFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding as FragmentScheduleBinding
        setupRecycle()
        subscribeToViewModel()
    }

    private fun setupRecycle() {
        binding.rvMatches.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = matchAdapter
        }
    }

    private fun subscribeToViewModel() {
        viewModel.observeMatches().subscribe {
            matchAdapter.setDataWithDiff(it)
        }.addTo(compositeDisposable)
    }
}