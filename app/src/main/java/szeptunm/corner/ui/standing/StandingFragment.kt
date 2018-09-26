package szeptunm.corner.ui.standing

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import szeptunm.corner.R
import szeptunm.corner.databinding.FragmentStandingBinding
import szeptunm.corner.ui.BaseFragment
import javax.inject.Inject

class StandingFragment : BaseFragment() {

    companion object {
        fun newInstance(): StandingFragment = StandingFragment()
    }

    @Inject
    lateinit var standingAdapter: StandingAdapter

    @Inject
    lateinit var viewModel: StandingViewModel

    private lateinit var binding: FragmentStandingBinding

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override val layoutResource: Int
        get() = R.layout.fragment_standing

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = viewDataBinding as FragmentStandingBinding
        setupRecycle()
        subscribeToViewModel()
    }

    private fun setupRecycle() {
        binding.rvStanding.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = standingAdapter
        }
    }

    private fun subscribeToViewModel() {
        viewModel.observeStandings().subscribe {
            standingAdapter.setData(it)
        }.addTo(compositeDisposable)
    }



}