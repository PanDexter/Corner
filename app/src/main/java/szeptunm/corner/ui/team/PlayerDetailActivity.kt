package szeptunm.corner.ui.team

import android.graphics.drawable.Drawable
import android.os.Bundle
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
import szeptunm.corner.databinding.PlayerDetailBinding
import szeptunm.corner.domain.splashScreen.GetClubInfoByName
import szeptunm.corner.entity.Player
import szeptunm.corner.ui.BaseActivity
import szeptunm.corner.ui.splashScreen.GetClubInfoFromPrefs
import szeptunm.corner.ui.team.PlayerViewHolder.Companion.KEY_PLAYER
import javax.inject.Inject

class PlayerDetailActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.player_detail

    @Inject
    lateinit var getClubInfoFromPrefs: GetClubInfoFromPrefs

    @Inject
    lateinit var getClubInfoByName: GetClubInfoByName

    private val clubName: String?
        get() = getClubInfoFromPrefs.getClubName()

    private lateinit var binding: PlayerDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.player_detail)
        val player = intent.getParcelableExtra(KEY_PLAYER) as Player
        supportPostponeEnterTransition()
        setupPlayerDetails(player)
        setToolbar()
        setImage(player)
    }

    private fun setImage(player: Player) {
        val requestOptions = RequestOptions()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .priority(LOW)

        Glide.with(binding.root)
                .load(getImage(player))
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
                .apply(requestOptions).into(binding.playerPhoto)
    }

    private fun getImage(player: Player): String? {
        return if (player.cutOutUrl == null) player.thumbUrl else player.cutOutUrl
    }

    private fun setupPlayerDetails(player: Player) {
        with(binding) {
            name.text = player.name
            nationality.text = player.nationality
            dateOfBirth.text = player.dateOfBirth
            description.text = player.description
            position.text = player.position
            sizesInfo.text = player.height + "/" + player.weight
        }
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        clubName?.let {
            getClubInfoByName.execute(it)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(Schedulers.computation())
                    .subscribe { club ->
                        toolbar.background = ContextCompat.getDrawable(applicationContext, club.gradient)
                    }
        }
        supportActionBar?.title = null
        toolbar.setNavigationIcon(R.drawable.ic_close_white)
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