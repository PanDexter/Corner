package szeptunm.corner.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.DaggerFragment
import szeptunm.corner.R
import szeptunm.corner.databinding.PlayerDetailBinding
import szeptunm.corner.entity.Player

class PlayerDetailFragment : DaggerFragment() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.player_detail, container, false)
        val player = arguments?.getParcelable("KEY_PLAYER") as Player
        setImage(player)
        setupPlayerDetails(player)
        binding.toolbar.title = player.name
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        binding.toolbar.navigationIcon = resources.getDrawable(R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        return binding.root
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