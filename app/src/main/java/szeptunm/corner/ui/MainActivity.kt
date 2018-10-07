package szeptunm.corner.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import szeptunm.corner.R
import szeptunm.corner.R.id
import szeptunm.corner.R.layout
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.ui.news.NewsFragment
import szeptunm.corner.ui.schedule.MatchFragment
import szeptunm.corner.ui.splashScreen.SplashScreenActivity.Companion.KEY_CLUB_INFO
import szeptunm.corner.ui.standing.StandingFragment
import szeptunm.corner.ui.team.TeamFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {


    override val layoutResource: Int
        get() = R.layout.activity_main

    @Inject
    lateinit var fragmentChanger: FragmentChanger
    lateinit var clubInfo: ClubInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clubInfo = intent.getParcelableExtra(KEY_CLUB_INFO) as ClubInfo
        setContentView(layout.activity_main)
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_placeholder, NewsFragment.newInstance(clubInfo), "news")
                .commit()
        val navigation: BottomNavigationView = findViewById(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            id.navigation_news -> {
                fragmentChanger.changeFragments(this, R.id.fragment_placeholder,
                        "news") { NewsFragment.newInstance(clubInfo) }
                return@OnNavigationItemSelectedListener true
            }
            id.navigation_scoreboard -> {
                fragmentChanger.changeFragments(this, R.id.fragment_placeholder,
                        "schedule") { MatchFragment.newInstance(clubInfo) }
                return@OnNavigationItemSelectedListener true
            }
            id.navigation_settings -> {
                return@OnNavigationItemSelectedListener true
            }
            id.navigation_standing -> {
                fragmentChanger.changeFragments(this, R.id.fragment_placeholder,
                        "standing") { StandingFragment.newInstance(clubInfo) }
                return@OnNavigationItemSelectedListener true
            }
            id.navigation_team -> {
                fragmentChanger.changeFragments(this, R.id.fragment_placeholder,
                        "team") { TeamFragment.newInstance(clubInfo) }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}


