package szeptunm.corner.ui.standing

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import szeptunm.corner.R
import szeptunm.corner.databinding.FragmentStandingBinding
import szeptunm.corner.entity.ClubInfo
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

    @Inject
    lateinit var clubInfo: ClubInfo

    private lateinit var binding: FragmentStandingBinding

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override val layoutResource: Int
        get() = R.layout.fragment_standing

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = viewDataBinding as FragmentStandingBinding
        setupRecycle()
        setLogo()
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

    private fun setLogo() {
        when (clubInfo.competitionId) {
            2014 -> binding.leagueCrest.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.primera_division))
            2021 -> binding.leagueCrest.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.premier_league))
            2002 -> binding.leagueCrest.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.bundesliga))
            2019 -> binding.leagueCrest.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.serie_a))
            2015 -> binding.leagueCrest.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ligue_1))
        }
    }
}