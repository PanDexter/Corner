package szeptunm.corner.ui.standing

import szeptunm.corner.ui.recycler.BindingViewHolder

class StandingViewHolder(binding: TableItemBinding) : BindingViewHolder<StandingItem, TableItemBinding>(binding) {

    lateinit var item: StandingItem

    override fun bind(item: StandingItem) {
        this.item = item
    }
}