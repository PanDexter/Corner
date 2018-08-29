package szeptunm.corner.ui.schedule

import szeptunm.corner.entity.Match
import szeptunm.corner.ui.recycler.AdapterItem

data class MatchItem(val match: Match) : AdapterItem {
    override fun getItepType(): Int = 0
}