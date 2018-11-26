package szeptunm.corner.ui.team

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import szeptunm.corner.R
import szeptunm.corner.databinding.FragmentTeamBinding
import szeptunm.corner.ui.BaseFragment
import timber.log.Timber
import javax.inject.Inject

class TeamFragment : BaseFragment() {
    override val layoutResource: Int
        get() = R.layout.fragment_team

    @Inject
    lateinit var playerAdapter: PlayerAdapter

    @Inject
    lateinit var viewModel: TeamViewModel

    private lateinit var binding: FragmentTeamBinding
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        fun newInstance(): TeamFragment = TeamFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding as FragmentTeamBinding
        setupRecycle()
        subscribeToViewModel()
    }

    private fun setupRecycle() {
        binding.rvTeam.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = playerAdapter
        }
    }

    private fun subscribeToViewModel() {
        viewModel.observePlayers()
                .subscribe({
                    playerAdapter.setDataWithDiff(it)
                }, { Timber.e(it, "Something went wrong during subscribing to team ViewModel") })
                .addTo(compositeDisposable)
    }
}