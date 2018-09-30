package szeptunm.corner.ui.news

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import szeptunm.corner.R
import szeptunm.corner.databinding.NewsWebBinding
import szeptunm.corner.entity.News
import szeptunm.corner.ui.BaseActivity

class NewsWebActivity : BaseActivity() {

    override val layoutResource: Int
        get() = R.layout.news_web

    private lateinit var binding: NewsWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.news_web)
        val news = intent.getParcelableExtra(NewsViewHolder.KEY_NEWS) as News
        binding.webView.loadUrl(news.link)
        setToolbar()
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = null;
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