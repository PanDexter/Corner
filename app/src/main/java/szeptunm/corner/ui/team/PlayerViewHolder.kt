package szeptunm.corner.ui.team

import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority.LOW
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import io.reactivex.subjects.PublishSubject
import szeptunm.corner.R
import szeptunm.corner.databinding.PlayerItemBinding
import szeptunm.corner.ui.recycler.BindingViewHolder

class PlayerViewHolder(binding: PlayerItemBinding, val itemObserver: PublishSubject<Int>,
        val fragment: TeamFragment) :
        BindingViewHolder<PlayerItem, PlayerItemBinding>(binding) {

    companion object {
        const val KEY_PLAYER = "KEY_PLAYER"
    }

    lateinit var item: PlayerItem

    override fun bind(item: PlayerItem) {
        this.item = item

        binding.containerPlayer.setOnClickListener {
            //            val intent = Intent(context, PlayerDetailActivity::class.java)
//            val bundle = Bundle()
//            bundle.putParcelable(KEY_PLAYER, item.player)
//            intent.putExtras(bundle)
//            context.startActivity(intent)
            prepareTransition()
        }
        val requestOptions = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .priority(LOW)
                .placeholder(R.drawable.fc_barcelona_badge)
        val photo: String? = getImage()
        Glide.with(itemView).load(photo).apply(requestOptions).into(binding.playerPhoto)
        binding.playerName.text = item.player.name
        binding.playerPosition.text = item.player.position
    }

    private fun getImage(): String? {
        return if (item.player.cutOutUrl == null) item.player.thumbUrl else item.player.cutOutUrl
    }

    private fun prepareTransition() {
        val intent = Intent(context, PlayerDetailActivity::class.java)
        intent.putExtra(KEY_PLAYER, item.player)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(fragment.requireActivity(),
                binding.playerPhoto, "playerPhoto")
        context.startActivity(intent, options.toBundle())
    }
}