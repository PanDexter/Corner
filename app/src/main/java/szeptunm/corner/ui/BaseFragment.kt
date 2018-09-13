package szeptunm.corner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import szeptunm.corner.R
import timber.log.Timber

abstract class BaseFragment : DaggerFragment() {

    companion object {
        private val Z_INDEX_ANIMATIONS = arrayOf(R.anim.slide_in_bottom)
    }

    protected lateinit var viewDataBinding: ViewDataBinding

    protected val onStopDisposable = CompositeDisposable()
    protected val onDestroyViewDisposable = CompositeDisposable()
    protected val onDestroyDisposable = CompositeDisposable()

    protected val baseActivity: BaseActivity?
        get() = activity as BaseActivity

    @get:LayoutRes
    protected abstract val layoutResource: Int

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("%s created", javaClass.simpleName)
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        Timber.i("%s view created", javaClass.simpleName)
        return viewDataBinding.root
    }

    @CallSuper
    override fun onStop() {
        Timber.i("%s stopped", javaClass.simpleName)
        onStopDisposable.clear()
        super.onStop()
    }

    @CallSuper
    override fun onDestroyView() {
        Timber.i("%s view destroyed", javaClass.simpleName)
        onDestroyViewDisposable.clear()
        super.onDestroyView()
    }

    @CallSuper
    override fun onDestroy() {
        Timber.i("%s destroyed", javaClass.simpleName)
        onDestroyDisposable.clear()
        super.onDestroy()
    }

    /**
     * Animation of entering fragment is performed below current fragment of the container.
     * In order to solve this we have to change the Z INDEX of the new fragment.
     * This function will move the new fragment up in z hierarchy,
     * whenever one of the [Z_INDEX_ANIMATIONS] is passed as an animation
     *
     * If this solution won't be sufficient, implement the one provided below
     * @see <a href="https://carlrice.io/blog/fragmenttransaction-z-index">Fragment animation z-index</a>
     */
    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        val viewTranslation = if (Z_INDEX_ANIMATIONS.contains(nextAnim)) 1f else 0f
        view?.let { ViewCompat.setTranslationZ(it, viewTranslation) }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }
}