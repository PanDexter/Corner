package szeptunm.corner.ui


import io.reactivex.disposables.CompositeDisposable
import androidx.databinding.ViewDataBinding
import dagger.android.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    lateinit var viewDataBinding: ViewDataBinding

    protected var compositeDisposable = CompositeDisposable()

}
