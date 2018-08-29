package szeptunm.corner.ui.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.PublishSubject
import szeptunm.corner.databinding.MatchItemBinding
import szeptunm.corner.ui.recycler.BindingViewHolder
import javax.inject.Inject

class MatchAdapter @Inject constructor() : RecyclerView.Adapter<BindingViewHolder<MatchItem, MatchItemBinding>>() {

    var items: List<MatchItem> = ArrayList()
    private var onItemSelected: PublishSubject<Int> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup,
            viewType: Int): BindingViewHolder<MatchItem, MatchItemBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MatchItemBinding.inflate(inflater, parent, false)
        return MatchViewHolder(binding, onItemSelected)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BindingViewHolder<MatchItem, MatchItemBinding>, position: Int) {
        holder.bind(items[holder.adapterPosition])
    }

    fun setDataWithDiff(matchList: List<MatchItem>) {
        this.items = matchList
        notifyDataSetChanged()
    }
}