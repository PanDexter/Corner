package szeptunm.corner.ui.schedule

import szeptunm.corner.entity.MatchSchedule
import szeptunm.corner.ui.recycler.AdapterItem

data class MatchItem(val match: MatchSchedule) : AdapterItem {
    override fun getItepType(): Int = 0
}