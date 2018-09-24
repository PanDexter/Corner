package szeptunm.corner.ui.standing

import szeptunm.corner.databinding.StandingItemBinding
import szeptunm.corner.ui.recycler.BindingViewHolder

class StandingViewHolder(binding: StandingItemBinding) :
        BindingViewHolder<StandingItem, StandingItemBinding>(binding) {

    lateinit var item: StandingItem

    override fun bind(item: StandingItem) {
        this.item = item
        with(binding) {
            clubName.text = item.table.teamId.toString()
            position.text = item.table.position.toString()
            played.text = item.table.playedGames.toString()
            won.text = item.table.won.toString()
            drown.text = item.table.draw.toString()
            lost.text = item.table.lost.toString()
            goalsFor.text = item.table.goalsFor.toString()
            goalsAgainst.text = item.table.goalAgainst.toString()
            goalsDifference.text = (item.table.goalAgainst?.let { item.table.goalsFor?.minus(it).toString() })
            points.text = item.table.points.toString()
        }
    }
}