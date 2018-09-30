package szeptunm.corner.ui.team

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import szeptunm.corner.R
import szeptunm.corner.databinding.PlayerDetailBinding
import szeptunm.corner.entity.Player
import szeptunm.corner.ui.BaseActivity
import szeptunm.corner.ui.team.PlayerViewHolder.Companion.KEY_PLAYER

class PlayerDetailActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.player_detail

    private lateinit var binding: PlayerDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.player_detail)
        val player = intent.getParcelableExtra(KEY_PLAYER) as Player
        setImage(player)
        setupPlayerDetails(player)
        setToolbar()
    }

    private fun setImage(player: Player) {
        val requestOptions = RequestOptions()
                .fitCenter()
        Glide.with(binding.root).load(player.thumbUrl).apply(requestOptions).into(binding.playerPhoto)
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
        supportActionBar?.title = null;
        toolbar.setNavigationIcon(R.drawable.ic_close_white)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}