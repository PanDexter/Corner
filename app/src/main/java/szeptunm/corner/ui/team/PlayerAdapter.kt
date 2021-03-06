package szeptunm.corner.ui.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import szeptunm.corner.databinding.PlayerItemBinding
import szeptunm.corner.domain.splashScreen.GetClubInfoByName
import szeptunm.corner.ui.recycler.BindingViewHolder
import javax.inject.Inject

class PlayerAdapter @Inject constructor(val fragment: TeamFragment, val getClubInfoByName: GetClubInfoByName) :
        RecyclerView.Adapter<BindingViewHolder<PlayerItem, PlayerItemBinding>>() {

    var items: List<PlayerItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup,
            viewType: Int): BindingViewHolder<PlayerItem, PlayerItemBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlayerItemBinding.inflate(inflater, parent, false)
        return PlayerViewHolder(binding, fragment, getClubInfoByName)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BindingViewHolder<PlayerItem, PlayerItemBinding>, position: Int) {
        holder.bind(items[holder.adapterPosition])
    }

    fun setDataWithDiff(playerList: List<PlayerItem>) {
        this.items = playerList
        notifyDataSetChanged()
    }
}