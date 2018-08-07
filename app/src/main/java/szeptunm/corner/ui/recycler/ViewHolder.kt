package szeptunm.corner.ui.recycler

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolder<T : AdapterItem>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected val context: Context

    init {
        context = itemView.context
    }

    abstract fun bind(item: T)
}