package szeptunm.corner.ui.schedule

import androidx.annotation.IntDef
import szeptunm.corner.entity.MatchSchedule
import szeptunm.corner.ui.recycler.AdapterItem
import kotlin.annotation.AnnotationRetention.SOURCE

data class MatchItem(val type: Int, val match: MatchSchedule) : AdapterItem {

    companion object {
        const val MATCH_PAST = 1
        const val MATCH_FUTURE = 2
    }

    @IntDef(MATCH_PAST, MATCH_FUTURE)
    @Retention(SOURCE)
    annotation class Type

    override fun getItemType(): Int = type
}