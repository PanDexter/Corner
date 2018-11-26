package szeptunm.corner.ui

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import szeptunm.corner.R
import szeptunm.corner.R.id
import szeptunm.corner.R.layout
import szeptunm.corner.commons.Constants.KEY_CLUB_NAME
import szeptunm.corner.commons.Utils
import szeptunm.corner.commons.broadcast.ConnectivityReceiver
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.ui.news.NewsFragment
import szeptunm.corner.ui.schedule.ScheduleFragment
import szeptunm.corner.ui.settings.SettingsFragment
import szeptunm.corner.ui.standing.StandingFragment
import szeptunm.corner.ui.team.TeamFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : BaseActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        TODO("SHOW ERROR AND BLOCK UI")
    }

    companion object {
        const val DOUBLE_BACK_PRESSED_SECONDS = 2L
    }
    override val layoutResource: Int
        get() = R.layout.activity_main

    @Inject
    lateinit var fragmentChanger: FragmentChanger

    private val compositeDisposable = CompositeDisposable()

    private var isFirstBackPressed = false

    val clubInfo: ClubInfo
        get() = intent.getParcelableExtra(KEY_CLUB_NAME)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        registerReceiver(ConnectivityReceiver(),
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_placeholder, NewsFragment.newInstance(), "news")
                .commit()
        val navigation: BottomNavigationView = findViewById(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            id.navigation_news -> {
                fragmentChanger.changeFragments(this, R.id.fragment_placeholder,
                        "news") { NewsFragment.newInstance() }
                return@OnNavigationItemSelectedListener true
            }
            id.navigation_scoreboard -> {
                fragmentChanger.changeFragments(this, R.id.fragment_placeholder,
                        "schedule") { ScheduleFragment.newInstance() }
                return@OnNavigationItemSelectedListener true
            }
            id.navigation_settings -> {
                fragmentChanger.changeFragments(this, R.id.fragment_placeholder,
                        "settings") { SettingsFragment.newInstance() }
                return@OnNavigationItemSelectedListener true
            }
            id.navigation_standing -> {
                fragmentChanger.changeFragments(this, R.id.fragment_placeholder,
                        "standing") { StandingFragment.newInstance() }
                return@OnNavigationItemSelectedListener true
            }
            id.navigation_team -> {
                fragmentChanger.changeFragments(this, R.id.fragment_placeholder,
                        "team") { TeamFragment.newInstance() }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onBackPressed() {
        when {
            isFirstBackPressed -> finish()
            else -> scheduleDoubleBackPress()
        }
    }

    private fun scheduleDoubleBackPress() {
        isFirstBackPressed = true
        Toast.makeText(this, R.string.double_back_pressed, Toast.LENGTH_SHORT).show()
        Completable.timer(DOUBLE_BACK_PRESSED_SECONDS, TimeUnit.SECONDS)
                .subscribe { isFirstBackPressed = false }
                .addTo(compositeDisposable)
    }
}


