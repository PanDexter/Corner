package szeptunm.corner.ui.standing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import szeptunm.corner.ui.recycler.BindingViewHolder
import javax.inject.Inject

class StandingAdapter @Inject constructor() :
        RecyclerView.Adapter<BindingViewHolder<StandingItem, TableItemBinding>>() {

    var items: List<StandingItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup,
            viewType: Int): BindingViewHolder<StandingItem, TableItemBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TableItemBinding.inflate(inflater, parent, false)
        return StandingViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BindingViewHolder<StandingItem, TableItemBinding>, position: Int) {
        holder.bind(items[holder.getAdapterPosition()])
    }

    fun setData(standingList: List<StandingItem>) {
        this.items = standingList
        notifyDataSetChanged()
    }
}