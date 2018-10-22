package szeptunm.corner.ui.schedule

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import szeptunm.corner.R
import szeptunm.corner.databinding.FragmentScheduleBinding
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.ui.BaseFragment
import javax.inject.Inject

class ScheduleFragment : BaseFragment() {
    override val layoutResource: Int
        get() = R.layout.fragment_schedule

    @Inject
    lateinit var scheduleAdapter: ScheduleAdapter

    @Inject
    lateinit var viewModel: ScheduleViewModel

    @Inject
    lateinit var clubInfo: ClubInfo

    private lateinit var binding: FragmentScheduleBinding
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        fun newInstance(): ScheduleFragment = ScheduleFragment()
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
            adapter = scheduleAdapter
        }
    }

    private fun subscribeToViewModel() {
        viewModel.observeMatches().subscribe {
            scheduleAdapter.setDataWithDiff(it)
        }.addTo(compositeDisposable)
    }
}