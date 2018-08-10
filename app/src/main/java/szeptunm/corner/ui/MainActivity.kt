package szeptunm.corner.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import szeptunm.corner.R
import szeptunm.corner.R.id
import szeptunm.corner.R.layout
import szeptunm.corner.databinding.ActivityMainBinding
import szeptunm.corner.ui.news.NewsFragment

class MainActivity : AppCompatActivity() {


    lateinit var binding:ActivityMainBinding

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            id.navigation_news -> {
                var newsFragment:NewsFragment = NewsFragment().newInstance()
                supportFragmentManager.beginTransaction().add(R.id.fragment_placeholder, newsFragment,"news").commitAllowingStateLoss()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        var newsFragment:NewsFragment = NewsFragment().newInstance()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navigation = findViewById<View>(id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
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

}
