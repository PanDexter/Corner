package szeptunm.corner.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.team_banner.view.team_name
import kotlinx.android.synthetic.main.team_banner.view.team_photo
import szeptunm.corner.R
import szeptunm.corner.commons.Constants
import szeptunm.corner.databinding.FragmentNewsBinding
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.ui.BaseFragment
import szeptunm.corner.ui.splashScreen.SplashScreenActivity
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
        binding.banner.setOnClickListener {
            val popUp = PopupMenu(context, it, 0)
            popUp.menuInflater.inflate(R.menu.popup_menu, popUp.menu)
            popUp.menu.forEach {
            }

            popUp.setOnMenuItemClickListener { menuItem ->
                viewModel.setNewClubName(menuItem.title.toString())
                restartApp(menuItem.title.toString())
                true
            }
            popUp.show()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.init().apply {
                binding.swipeRefresh.isRefreshing = false
            }
        }
        subscribeToViewModel()
        setupRecycle()
    }

    private fun setupRecycle() {
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    private fun subscribeToViewModel() {
        viewModel.observeNews().subscribe {
            newsAdapter.setDataWithDiff(it)
        }.addTo(compositeDisposable)
    }

    private fun restartApp(clubName: String) {
        val intent = Intent(activity, SplashScreenActivity::class.java)
        val bundle = Bundle().apply {
            putString(Constants.KEY_CLUB_NAME, clubName)
            putBoolean(Constants.IS_DURING_FLOW, true)
        }
        intent.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity?.finish()
        startActivity(intent)
    }
}