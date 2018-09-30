package szeptunm.corner.ui.standing

import szeptunm.corner.entity.Competition
import szeptunm.corner.entity.Standing
import szeptunm.corner.entity.Team
import szeptunm.corner.ui.recycler.AdapterItem

class StandingItem(val table: Standing, val team: Team, val competition: Competition) : AdapterItem {
    override fun getItemType(): Int = 0
}