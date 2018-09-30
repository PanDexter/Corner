package szeptunm.corner.ui.standing

import androidx.core.content.ContextCompat
import szeptunm.corner.R
import szeptunm.corner.databinding.StandingItemBinding
import szeptunm.corner.ui.recycler.BindingViewHolder

class StandingViewHolder(binding: StandingItemBinding) :
        BindingViewHolder<StandingItem, StandingItemBinding>(binding) {

    lateinit var item: StandingItem

    override fun bind(item: StandingItem) {
        this.item = item
        with(binding) {
            if (adapterPosition % 2 == 0) {
                standingItem.setBackgroundColor(ContextCompat.getColor(context, R.color.standing_item))
            }
            clubName.text = item.team.name
            position.text = item.table.position.toString()
            played.text = item.table.playedGames.toString()
            won.text = item.table.won.toString()
            drown.text = item.table.draw.toString()
            lost.text = item.table.lost.toString()
            goalsDifference.text = item.table.goalsDifference.toString()
            points.text = item.table.points.toString()
        }
    }
}