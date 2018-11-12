package szeptunm.corner.ui.news

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import android.text.util.Linkify
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority.LOW
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import io.reactivex.schedulers.Schedulers
import szeptunm.corner.R
import szeptunm.corner.R.layout
import szeptunm.corner.databinding.NewsDetailsBinding
import szeptunm.corner.domain.splashScreen.GetClubInfoByName
import szeptunm.corner.entity.News
import szeptunm.corner.ui.BaseActivity
import szeptunm.corner.ui.news.NewsViewHolder.Companion.KEY_NEWS
import szeptunm.corner.ui.splashScreen.GetClubInfoFromPrefs
import javax.inject.Inject

class NewsDetailActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.news_details

    lateinit var binding: NewsDetailsBinding

    @Inject
    lateinit var getClubInfoFromPrefs: GetClubInfoFromPrefs

    @Inject
    lateinit var getClubInfoByName: GetClubInfoByName

    private val clubName: String?
        get() = getClubInfoFromPrefs.getClubName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.news_details)
        binding = DataBindingUtil.setContentView(this, R.layout.news_details)
        val news = intent.getParcelableExtra(KEY_NEWS) as News
        supportPostponeEnterTransition()
        binding.newsDetailsDescription.text = "${Html.fromHtml(news.description)}\n Read more: \n ${news.link}"
        Linkify.addLinks(binding.newsDetailsDescription, Linkify.ALL)
        binding.newsDetailsDescription.linksClickable = true
        setToolbar()
        setImage(news)
    }

    private fun setImage(news: News) {
        val requestOptions = RequestOptions()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .priority(LOW)

        Glide.with(binding.root)
                .load(news.photoUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                            dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?,
                            isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                })
                .apply(requestOptions).into(binding.newsDetailsPhoto)
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        clubName?.let {
            getClubInfoByName.execute(it)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(Schedulers.computation())
                    .subscribe { club ->
                        toolbar.background = ContextCompat.getDrawable(applicationContext, club.gradient)
                    }
        }
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

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }

}