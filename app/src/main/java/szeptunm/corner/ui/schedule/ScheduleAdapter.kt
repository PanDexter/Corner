package szeptunm.corner.ui.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import szeptunm.corner.databinding.MatchItemBinding
import szeptunm.corner.databinding.MatchNextBinding
import szeptunm.corner.ui.recycler.ViewHolder
import szeptunm.corner.ui.schedule.MatchItem.Companion.MATCH_FUTURE
import szeptunm.corner.ui.schedule.MatchItem.Companion.MATCH_PAST
import javax.inject.Inject

class ScheduleAdapter @Inject constructor() : RecyclerView.Adapter<ViewHolder<MatchItem>>() {

    var items: List<MatchItem> = ArrayList()

    private fun getViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            MATCH_PAST -> {
                getMatchPastViewHolder(inflater, parent)
            }
            MATCH_FUTURE -> {
                getMatchFutureViewHolder(inflater, parent)
            }
            else -> throw IllegalArgumentException("This viewType is not supported $viewType")
        }
    }

    private fun getMatchPastViewHolder(inflater: LayoutInflater, parent: ViewGroup): MatchPastViewHolder {
        val binding = MatchItemBinding.inflate(inflater, parent, false)
        return MatchPastViewHolder(binding)
    }

    private fun getMatchFutureViewHolder(inflater: LayoutInflater, parent: ViewGroup): MatchFutureViewHolder {
        val binding = MatchNextBinding.inflate(inflater, parent, false)
        return MatchFutureViewHolder(binding)
    }

    @MatchItem.Type
    override fun getItemViewType(position: Int): Int = items[position].type

    override fun onCreateViewHolder(parent: ViewGroup,
            viewType: Int): ViewHolder<MatchItem> {
        return getViewHolder(parent, viewType) as ViewHolder<MatchItem>
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder<MatchItem>, position: Int) {
        holder.bind(items[holder.adapterPosition])
    }

    fun setDataWithDiff(matchList: List<MatchItem>) {
        this.items = matchList
        notifyDataSetChanged()
    }
}