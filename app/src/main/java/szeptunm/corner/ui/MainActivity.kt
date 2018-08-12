package szeptunm.corner.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity
import szeptunm.corner.R
import szeptunm.corner.R.id
import szeptunm.corner.R.layout
import szeptunm.corner.ui.news.NewsFragment
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var fragmentChanger: FragmentChanger

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(layout.activity_main)
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_placeholder, NewsFragment.newInstance(), "news")
                .commit()
        val navigation: BottomNavigationView = findViewById(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        super.onCreate(savedInstanceState)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            id.navigation_news -> {
                fragmentChanger.changeFragments(this, R.id.fragment_placeholder,
                        "news") { NewsFragment.newInstance() }
                return@OnNavigationItemSelectedListener true
            }
            id.navigation_scoreboard -> {
                //fragmentChanger.changeFragments(this,R.id.fragment_placeholder, "scoreboard"){}
                return@OnNavigationItemSelectedListener true
            }
            id.navigation_settings -> {
                return@OnNavigationItemSelectedListener true
            }
            id.navigation_standing -> {
                return@OnNavigationItemSelectedListener true
            }
            id.navigation_team -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}


