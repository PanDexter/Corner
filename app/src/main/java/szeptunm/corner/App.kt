package szeptunm.corner

import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import szeptunm.corner.di.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
                .apply { seedInstance(this@App)}
                .build()

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }

}