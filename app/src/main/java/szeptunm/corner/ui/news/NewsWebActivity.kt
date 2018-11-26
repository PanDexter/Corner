package szeptunm.corner.ui.news

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import io.reactivex.schedulers.Schedulers
import szeptunm.corner.R
import szeptunm.corner.databinding.NewsWebBinding
import szeptunm.corner.domain.splashScreen.GetClubInfoByName
import szeptunm.corner.entity.News
import szeptunm.corner.ui.BaseActivity
import szeptunm.corner.ui.splashScreen.GetClubInfoFromPrefs
import timber.log.Timber
import javax.inject.Inject

class NewsWebActivity : BaseActivity() {

    override val layoutResource: Int
        get() = R.layout.news_web

    private lateinit var binding: NewsWebBinding

    @Inject
    lateinit var getClubInfoFromPrefs: GetClubInfoFromPrefs

    @Inject
    lateinit var getClubInfoByName: GetClubInfoByName

    private val clubName: String?
        get() = getClubInfoFromPrefs.getClubName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.news_web)
        val news = intent.getParcelableExtra(NewsViewHolder.KEY_NEWS) as News
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(news.link)
        setToolbar()
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        clubName?.let { clubName ->
            getClubInfoByName.execute(clubName)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(Schedulers.computation())
                    .subscribe({ club ->
                        toolbar.background = ContextCompat.getDrawable(applicationContext, club.gradient)
                    }, { Timber.e(it, "Something went wrong during web activity") })
        }
        supportActionBar?.title = null
        toolbar.setNavigationIcon(R.drawable.ic_close_white)
        toolbar.title = ""
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}