package szeptunm.corner.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber

abstract class BaseActivity : DaggerAppCompatActivity() {

    lateinit var viewDataBinding: ViewDataBinding

    abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("%s created", javaClass.simpleName)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResource)
    }

    override fun onDestroy() {
        Timber.i("%s destroyed", javaClass.simpleName)
        super.onDestroy()
    }
}
