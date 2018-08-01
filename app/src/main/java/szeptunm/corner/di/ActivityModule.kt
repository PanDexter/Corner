package szeptunm.corner.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import szeptunm.corner.ui.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity():MainActivity
}