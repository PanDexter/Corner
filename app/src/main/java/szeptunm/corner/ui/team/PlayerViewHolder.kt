package szeptunm.corner.ui.team

import com.bumptech.glide.Glide
import com.bumptech.glide.Priority.LOW
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import io.reactivex.subjects.PublishSubject
import szeptunm.corner.R
import szeptunm.corner.databinding.PlayerItemBinding
import szeptunm.corner.ui.FragmentChanger
import szeptunm.corner.ui.MainActivity
import szeptunm.corner.ui.recycler.BindingViewHolder

class PlayerViewHolder(binding: PlayerItemBinding, val itemObserver: PublishSubject<Int>) :
        BindingViewHolder<PlayerItem, PlayerItemBinding>(binding) {

    lateinit var item: PlayerItem

    override fun bind(item: PlayerItem) {
        this.item = item

        val fragmentChanger = FragmentChanger()

        val activity = context as MainActivity
        binding.containerPlayer.setOnClickListener {
            fragmentChanger.changeFragments(activity, R.id.fragment_placeholder,
                    "playerDetail") { PlayerDetailFragment.newInstance(item.player) }
        }
        val requestOptions = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .priority(LOW)
                .placeholder(R.drawable.barcelonabadge)
        val photo: String? = getImage()
        Glide.with(itemView).load(photo).apply(requestOptions).into(binding.playerPhoto)
        binding.playerName.text = item.player.name
        binding.playerPosition.text = item.player.position
    }

    private fun getImage(): String? {
        return if (item.player.cutOutUrl == null) item.player.thumbUrl else item.player.cutOutUrl
    }
}