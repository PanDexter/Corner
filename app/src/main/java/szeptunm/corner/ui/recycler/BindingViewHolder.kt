package szeptunm.corner.ui.recycler

import androidx.databinding.ViewDataBinding

abstract class BindingViewHolder<T : AdapterItem, K : ViewDataBinding>(protected var binding: K) :
        ViewHolder<T>(binding.root)