package szeptunm.corner.ui.team

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import szeptunm.corner.R
import szeptunm.corner.databinding.PlayerDetailBinding
import szeptunm.corner.entity.Player
import szeptunm.corner.ui.ToolbarFragment

class PlayerDetailFragment : ToolbarFragment() {
    override val layoutResource: Int
        get() = R.layout.player_detail

    private lateinit var binding: PlayerDetailBinding

    companion object {
        fun newInstance(player: Player): PlayerDetailFragment {
            val fragment = PlayerDetailFragment()
            val args = Bundle()
            args.putParcelable("KEY_PLAYER", player)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding as PlayerDetailBinding
        val player = arguments?.getParcelable("KEY_PLAYER") as Player
        setImage(player)
        setupPlayerDetails(player)
        binding.toolbar.title = player.name
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))
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
}