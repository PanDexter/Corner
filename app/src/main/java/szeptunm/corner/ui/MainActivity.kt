package szeptunm.corner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity
import szeptunm.corner.R
import szeptunm.corner.R.id
import szeptunm.corner.R.layout
import szeptunm.corner.ui.news.NewsFragment

class MainActivity : DaggerAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(layout.activity_main)
        val navigation: BottomNavigationView = findViewById(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        super.onCreate(savedInstanceState)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            id.navigation_news -> {
                val newsFragmentManager: NewsFragment = NewsFragment.newInstance()
                supportFragmentManager.beginTransaction().add(R.id.fragment_placeholder, newsFragmentManager, "news")
                return@OnNavigationItemSelectedListener true
            }
            id.navigation_scoreboard -> {
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

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_placeholder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    fun handleFragment(tag: String?) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentToChange: Fragment? = supportFragmentManager.findFragmentByTag(tag)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_placeholder)

        if (fragmentToChange != null && currentFragment != null && currentFragment != fragmentToChange) {
            if (fragmentToChange.isDetached)
                fragmentToChange.let { transaction.detach(currentFragment).attach(it) }
        }
        transaction.commitNowAllowingStateLoss()
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
}

