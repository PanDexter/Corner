package szeptunm.corner.ui.team

import szeptunm.corner.entity.Player
import szeptunm.corner.ui.recycler.AdapterItem

data class PlayerItem(val player: Player) : AdapterItem {
    override fun getItemType(): Int = 0
}