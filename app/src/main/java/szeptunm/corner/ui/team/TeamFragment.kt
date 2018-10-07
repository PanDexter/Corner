package szeptunm.corner.ui.team

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import szeptunm.corner.R
import szeptunm.corner.databinding.FragmentTeamBinding
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.ui.BaseFragment
import szeptunm.corner.ui.splashScreen.SplashScreenActivity.Companion.KEY_CLUB_INFO
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
        fun newInstance(clubInfo: ClubInfo): TeamFragment =
                TeamFragment().apply {
                    arguments = bundleOf(KEY_CLUB_INFO to clubInfo)
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val clubInfo = arguments?.getParcelable(KEY_CLUB_INFO) as ClubInfo
        binding = viewDataBinding as FragmentTeamBinding
        viewModel.init(clubInfo)
        setupRecycle()
        subscribeToViewModel()
    }

    private fun setupRecycle() {
        binding.rvTeam.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = playerAdapter
            //addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun subscribeToViewModel() {
        viewModel.observePlayers().subscribe {
            playerAdapter.setDataWithDiff(it)
        }.addTo(compositeDisposable)
    }
}