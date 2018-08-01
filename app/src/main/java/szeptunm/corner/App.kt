package szeptunm.corner

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import szeptunm.corner.di.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
                .apply { seedInstance(this@App)}
                .build()

}