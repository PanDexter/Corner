package szeptunm.corner.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber

abstract class BaseActivity : DaggerAppCompatActivity() {


    abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("%s created", javaClass.simpleName)
    }

    override fun onDestroy() {
        Timber.i("%s destroyed", javaClass.simpleName)
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
