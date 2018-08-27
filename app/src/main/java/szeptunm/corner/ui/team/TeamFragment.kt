package szeptunm.corner.ui.team

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
import szeptunm.corner.databinding.FragmentTeamBinding
import javax.inject.Inject

class TeamFragment : DaggerFragment() {

    @Inject
    lateinit var playerAdapter: PlayerAdapter

    @Inject
    lateinit var viewModel: TeamViewModel

    private lateinit var binding: FragmentTeamBinding
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        fun newInstance(): TeamFragment = TeamFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_team, container, false)
        setupRecycle()
        subscribeToViewModel()
        return binding.root
    }

    private fun setupRecycle() {
        binding.rvTeam.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = playerAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun subscribeToViewModel() {
        viewModel.observePlayers().subscribe {
            playerAdapter.setDataWithDiff(it)
        }.addTo(compositeDisposable)
    }
}